package com.iremembr.jtraxxsexamples.adtmessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

class MessageMatcherTest {

    private static String toString(PasswordQualityMessage msg) {
        return msg.match(
                (PasswordTooShort pts) ->
                        format("Your password is too short. Minimal length is %s.", pts.minLength()),
                (MissingNumber mn) ->
                        "Your password must have at least one number.",
                (MissingUppercaseLetter mul) ->
                        "Your password must have at least one uppercase letter."
        );
    }

    private static void println(TestInfo testInfo, String text) {
        System.out.println(testInfo.getDisplayName() + ": " + text);
    }

    @Test
    void passwordTooShort(TestInfo testInfo) {
        PasswordTooShort message = new PasswordTooShort(5, 10);
        String text = toString(message);
        println(testInfo, text);

        assertThat(text).isEqualTo("Your password is too short. Minimal length is 10.");
    }

    @Test
    void missingNumber(TestInfo testInfo) {
        MissingNumber message = new MissingNumber();
        String text = toString(message);
        println(testInfo, text);

        assertThat(text).isEqualTo("Your password must have at least one number.");
    }

    @Test
    void missingUppercaseLetter(TestInfo testInfo) {
        MissingUppercaseLetter message = new MissingUppercaseLetter();
        String text = toString(message);
        println(testInfo, text);

        assertThat(text).isEqualTo("Your password must have at least one uppercase letter.");
    }
}
