package com.example.root.jobify.Services.Auth;

/**
 * Created by root on 08/10/16.
 */

public class User {

    private final String name;
    private final String email;
    private final String password;

    public User(String email,String password) {
        this.name = email;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
