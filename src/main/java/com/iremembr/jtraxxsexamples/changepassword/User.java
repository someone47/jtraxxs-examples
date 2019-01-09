package com.iremembr.jtraxxsexamples.changepassword;

public class User {

    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean isPassword(String password) {
        return password != null && password.equals(this.password);
    }

    void changePassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s[username=%s]", getClass().getSimpleName(), username);
    }
}
