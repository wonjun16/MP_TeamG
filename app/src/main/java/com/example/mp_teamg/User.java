package com.example.mp_teamg;

public class User {
    private String name;
    private String password;
    private String id;
    private String phone;

    public User(String name, String password, String id, String phone){
        this.name=name;
        this.password=password;
        this.id=id;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

}
