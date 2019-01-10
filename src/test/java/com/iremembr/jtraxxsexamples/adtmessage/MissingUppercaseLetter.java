package com.iremembr.jtraxxsexamples.adtmessage;

import java.util.function.Function;

public class MissingUppercaseLetter implements PasswordQualityMessage {

    @Override
    public <T> T match(
            Function<PasswordTooShort, T> tooShort,
            Function<MissingNumber, T> missingNumber,
            Function<MissingUppercaseLetter, T> missingUppercaseLetter
    ) {
        return missingUppercaseLetter.apply(this);
    }
}
