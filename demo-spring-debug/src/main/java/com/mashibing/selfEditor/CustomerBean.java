package com.mashibing.selfEditor;


import java.net.URI;

public class CustomerBean {

    private String name;

    // 自定义属性编辑器将识别并将配置文件中的配置属性解析然后注入该属性值。
    private AddressVo address;

    private URI uri;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

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
        return "CustomerBean{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", uri=" + uri +
                '}';
    }
}
