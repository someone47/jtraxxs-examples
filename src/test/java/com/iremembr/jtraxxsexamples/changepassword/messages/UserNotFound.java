package com.iremembr.jtraxxsexamples.changepassword.messages;

public class UserNotFound implements Message {

    private final String username;

    public UserNotFound(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }

    @Override
    public String text() {
        return String.format("User '%s' not found", username);
    }
}
