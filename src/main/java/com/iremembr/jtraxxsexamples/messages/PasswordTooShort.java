package com.iremembr.jtraxxsexamples.messages;

import java.util.function.Function;

public class PasswordTooShort implements PasswordQualityMessage {

    private final int length;
    private final int minLength;

    public PasswordTooShort(int length, int minLength) {
        this.length = length;
        this.minLength = minLength;
    }

    public int length() {
        return length;
    }

    public int minLength() {
        return minLength;
    }

    @Override
    public <T> T match(
            Function<PasswordTooShort, T> tooShort,
            Function<MissingNumber, T> missingNumber,
            Function<MissingUppercaseLetter, T> missingUppercaseLetter
    ) {
        return tooShort.apply(this);
    }
}
