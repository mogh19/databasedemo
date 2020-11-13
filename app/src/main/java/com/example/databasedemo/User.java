package com.example.databasedemo;

public class User {
    private String full_name;
    private String age;
    private String email;


    public User(String full_name, String age, String email) {
        this.full_name = full_name;
        this.age = age;
        this.email = email;
    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
