package com.suyh.utils.impl;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomBaseMapperProvider extends MapperTemplate {


    public CustomBaseMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 过滤(匹配)查询
     *
     * @param ms
     * @return
     */
    public SqlNode selectModelByFilter(MappedStatement ms) {
        String paramFilter = "filter";

        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        String tableName = this.tableName(entityClass);

        String selectAll = SqlHelper.selectAllColumns(entityClass);
        String fromTable = SqlHelper.fromTable(entityClass, tableName);


        List<SqlNode> sqlNodes = new ArrayList<>();
        sqlNodes.add(new StaticTextSqlNode(selectAll + fromTable));
        // where 标签中的条件判断语句，可以是模糊条件查询，也可是完全匹配。
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        SqlNode ifFilter = makeIfFilterNode(columns, paramFilter);
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), ifFilter);
        sqlNodes.add(whereSqlNode);
        return new MixedSqlNode(sqlNodes);
    }



    /**
     * 生成过滤条件的 SqlNode
     *
     *      <![CDATA[
     *         <if test="filter != null">
     *           <if test="filter.createdBy != null and filter.createdBy != '' ">
     *             AND created_by = #{filter.createdBy, jdbcType=NVARCHAR}
     *           </if>
     *         </if>
     *       ]]>
     *
     * @param columns
     * @param paramFilter
     * @return
     */
    public static SqlNode makeIfFilterNode(Set<EntityColumn> columns, String paramFilter) {
        List<SqlNode> ifNodes = new ArrayList<>();
        for (EntityColumn column : columns) {
            // AND created_by = #{filter.createdBy, jdbcType=NVARCHAR}
            String sqlText = String.format("AND %s = #{%s.%s, jdbcType = %s}",
                    column.getColumn(), paramFilter, column.getProperty(),
                    column.getJdbcType().toString());

            StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);
            if (column.getJavaType().equals(String.class)) {
                // filter.createdBy != null and filter.createdBy != ''
                String sqlTextString = String.format("%s.%s != null and %s.%s != ''",
                        paramFilter, column.getProperty(), paramFilter, column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextString));
            } else {
                // filter.createdBy != null
                String sqlTextOther = String.format("%s.%s != null",
                        paramFilter, column.getProperty());
                ifNodes.add(new IfSqlNode(columnNode, sqlTextOther));
            }
        }

        IfSqlNode ifFilter = new IfSqlNode(new MixedSqlNode(ifNodes),
                paramFilter + " != null");
        return ifFilter;
    }




    /**
     * 生成更新过滤条件的 SqlNode
     *      <![CDATA[
     *          <if test="model != null">
     *            <if test="null != model.uuid">
     *              UUID = #{model.uuid, jdbcType = NVARCHAR},
     *            </if>
     *          </if>
     *       ]]>
     *
     * @param entityClass
     * @param paramModel
     * @return
     */
    public static SqlNode makeIfModelNodes(Class<?> entityClass, String paramModel) {
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        List<SqlNode> ifNodes = new ArrayList<>();
        for (EntityColumn column : columns) {
            // UUID = #{model.uuid, jdbcType = NVARCHAR},
            String sqlText = String.format("%s = #{%s.%s, jdbcType = %s},",
                    column.getColumn(), paramModel, column.getProperty(),
                    column.getJdbcType().toString());

            StaticTextSqlNode columnNode = new StaticTextSqlNode(sqlText);
            // null != model.uuid
            String sqlTextString = String.format("null != %s.%s",
                    paramModel, column.getProperty());
            ifNodes.add(new IfSqlNode(columnNode, sqlTextString));
        }

        IfSqlNode ifFilter = new IfSqlNode(new MixedSqlNode(ifNodes),
                paramModel + " != null");
        return ifFilter;
    }


    /**
     * 更新记录，指定过滤条件
     *
     * @return
     */
    public SqlNode updateModelByFilter(MappedStatement ms) {
        String paramModel = "model";
        String paramFilter = "filter";

        Class<?> entityClass = getEntityClass(ms);
        setResultType(ms, entityClass);
        String tableName = this.tableName(entityClass);


        // 拼接要更新字段的SQL
        SqlNode sqlSetIfModel = makeIfModelNodes(entityClass, paramModel);
        // 填充到<set> 标签中
        SetSqlNode setSqlNode = new SetSqlNode(ms.getConfiguration(), sqlSetIfModel);
        // 拼接过滤条件字段的SQL
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        SqlNode ifFilterNode = makeIfFilterNode(columns, paramFilter);
        // 填充到<where> 标签中
        WhereSqlNode whereSqlNode = new WhereSqlNode(ms.getConfiguration(), ifFilterNode);

        // 拼接完整:
        // UPDATE tableName
        // <set>
        //      <if test="model != null">
        //          <if test="model.uuid != null">
        //              UUID = ${model.uuid, jdbcType = NVARCHAR},
        //          </if>
        //      </if>
        // </set>
        // <where>
        //      <if test="filter != null">
        //          <if test="filter.uuid != null and filter.uuid != ''">
        //              AND UUID = ${filter.uuid, jdbcType = NVARCHAR}
        //          </if>
        //      </if>
        // </where>
        List<SqlNode> sqlNodes = new ArrayList<>();
        sqlNodes.add(new StaticTextSqlNode(SqlHelper.updateTable(entityClass, tableName)));
        sqlNodes.add(setSqlNode);
        sqlNodes.add(whereSqlNode);
        return new MixedSqlNode(sqlNodes);
    }






}
