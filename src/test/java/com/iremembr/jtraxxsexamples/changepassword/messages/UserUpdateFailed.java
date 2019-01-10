package com.iremembr.jtraxxsexamples.changepassword.messages;

public class UserUpdateFailed implements Message {

    @Override
    public String text() {
        return "Could not change password";
    }
}
