package com.coffeine.virtuoso.module.security;

/**
 * Created by vitaliy on 6/22/14 15:30.
 */
public class User {
    protected String username;
    protected String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
