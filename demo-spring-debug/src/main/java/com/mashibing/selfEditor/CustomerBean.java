package com.mashibing.selfEditor;


public class CustomerBean {

    private String name;

    // 自定义属性编辑器将识别并将配置文件中的配置属性解析然后注入该属性值。
    private AddressVo address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressVo getAddress() {
        return address;
    }

    public void setAddress(AddressVo address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
