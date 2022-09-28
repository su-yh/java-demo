package com.mashibing.selfEditor;


public class Customer {

    private String name;

    // 自定义属性编辑器将识别并将配置文件中的配置属性解析然后注入该属性值。
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
