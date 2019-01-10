package com.iremembr.jtraxxsexamples.changepassword.messages;

public class WrongPassword implements Message {

    @Override
    public String text() {
        return "Wrong password";
    }
}
