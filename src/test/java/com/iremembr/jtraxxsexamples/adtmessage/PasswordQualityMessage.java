package com.iremembr.jtraxxsexamples.adtmessage;

import java.util.function.Function;

public interface PasswordQualityMessage {

    <T> T match(
            Function<PasswordTooShort, T> tooShort,
            Function<MissingNumber, T> missingNumber,
            Function<MissingUppercaseLetter, T> missingUppercaseLetter
    );
}
