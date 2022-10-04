package com.mashibing.factorybean;

public class SuyhUser {

    private String username;

    public SuyhUser() {
    }

    public SuyhUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}' + super.toString();
    }
}
