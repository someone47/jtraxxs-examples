package com.iremembr.jtraxxsexamples;

import com.iremembr.jtraxxs.ValueResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static com.iremembr.jtraxxs.ValueResult.fail;
import static com.iremembr.jtraxxs.ValueResult.ok;
import static org.assertj.core.api.Assertions.assertThat;

class NumberParserTest {

    private static ValueResult<Integer, String> parseInteger(String str) {
        try {
            return ok(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return fail("Can't parse string: " + str);
        }
    }

    private static void println(TestInfo testInfo, String text) {
        System.out.println(testInfo.getDisplayName() + ": " + text);
    }

    @Test
    void parseStringOk(TestInfo testInfo) {
        ValueResult<Integer, String> result = parseInteger("4711");

        result
                .onSuccess(i -> println(testInfo, "Number = " + i))
                .onFailure(str -> println(testInfo, "Can't parse string: " + str));

        assertThat(result.isSuccessful()).isTrue();
        assertThat(result.value()).isEqualTo(4711);
    }

    @Test
    void parseStringFail(TestInfo testInfo) {
        ValueResult<Integer, String> result = parseInteger("abc");

        result
                .onSuccess(i -> println(testInfo, "Number = " + i))
                .onFailure(str -> println(testInfo, "Can't parse string: " + str));

        assertThat(result.hasFailed()).isTrue();
        assertThat(result.error()).isEqualTo("Can't parse string: abc");
    }
}
