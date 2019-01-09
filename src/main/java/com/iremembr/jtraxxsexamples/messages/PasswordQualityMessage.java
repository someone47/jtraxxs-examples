package com.iremembr.jtraxxsexamples.messages;

import java.util.function.Function;

public interface PasswordQualityMessage {

    <T> T match(
            Function<PasswordTooShort, T> tooShort,
            Function<MissingNumber, T> missingNumber,
            Function<MissingUppercaseLetter, T> missingUppercaseLetter
    );
}
