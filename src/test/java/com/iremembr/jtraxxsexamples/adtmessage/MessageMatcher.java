package com.iremembr.jtraxxsexamples.adtmessage;

import static java.lang.String.format;

public class MessageMatcher {

    public static void main(String[] args) {
        PasswordTooShort message = new PasswordTooShort(5, 10);

        String text = toString(message);

        System.out.println(text);
    }

    public static String toString(PasswordQualityMessage msg) {
        return msg.match(
                (PasswordTooShort pts) ->
                        format("Your password is too short. Minimal length is %s.", pts.minLength()),
                (MissingNumber mn) ->
                        "Your password must have at least one number.",
                (MissingUppercaseLetter mul) ->
                        "Your password must have at least one uppercase letter."
        );
    }

}
