package com.iremembr.jtraxxsexamples.changepassword.messages;

public class BadPassword implements Message {

    @Override
    public String text() {
        return "Low password quality";
    }
}
