package com.iremembr.jtraxxsexamples.changepassword;

import com.iremembr.jtraxxs.ValueResult;
import com.iremembr.jtraxxs.VoidResult;
import com.iremembr.jtraxxsexamples.changepassword.messages.Message;
import com.iremembr.jtraxxsexamples.changepassword.messages.UserNotFound;
import com.iremembr.jtraxxsexamples.changepassword.messages.UserUpdateFailed;
import com.iremembr.jtraxxsexamples.changepassword.messages.WrongPassword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangePasswordTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    private static void println(TestInfo testInfo, String text) {
        System.out.println(testInfo.getDisplayName() + ": " + text);
    }

    @Test
    void success(TestInfo testInfo) {
        // Given
        User user = new User("joe", "1234secret");
        when(userRepository.find("joe")).thenReturn(ValueResult.ok(user));
        when(userRepository.update(user)).thenReturn(VoidResult.ok());

        // When
        VoidResult<Message> result = userService.changePassword("joe", "1234secret", "password567");

        result
                .onSuccess(() -> println(testInfo,"Password changed"))
                .onFailure(msg -> println(testInfo,msg.text()));

        // Then
        assertThat(result.isSuccessful()).isTrue();
        verify(userRepository).update(user);
    }

    @Test
    void unknownUser(TestInfo testInfo) {
        // Given
        User user = new User("joe", "1234secret");
        when(userRepository.find("joe")).thenReturn(ValueResult.fail(new UserNotFound("joe")));

        // When
        VoidResult<Message> result = userService.changePassword("joe", "1234secret", "password567");

        result
                .onSuccess(() -> println(testInfo,"Password changed"))
                .onFailure(msg -> println(testInfo,msg.text()));

        // Then
        assertThat(result.hasFailed()).isTrue();
        assertThat(result.error()).isExactlyInstanceOf(UserNotFound.class);
        assertThat(((UserNotFound) result.error()).username()).isEqualTo("joe");
        verify(userRepository, never()).update(any());
    }

    @Test
    void wrongPassword(TestInfo testInfo) {
        // Given
        User user = new User("joe", "wrong-password");
        when(userRepository.find("joe")).thenReturn(ValueResult.ok(user));

        // When
        VoidResult<Message> result = userService.changePassword("joe", "1234secret", "password567");

        result
                .onSuccess(() -> println(testInfo,"Password changed"))
                .onFailure(msg -> println(testInfo,msg.text()));

        // Then
        assertThat(result.hasFailed()).isTrue();
        assertThat(result.error()).isExactlyInstanceOf(WrongPassword.class);
        verify(userRepository, never()).update(any());
    }

    @Test
    void userUpdateFailed(TestInfo testInfo) {
        // Given
        User user = new User("joe", "1234secret");
        when(userRepository.find("joe")).thenReturn(ValueResult.ok(user));
        when(userRepository.update(user)).thenReturn(VoidResult.fail(new UserUpdateFailed()));

        // When
        VoidResult<Message> result = userService.changePassword("joe", "1234secret", "password567");

        result
                .onSuccess(() -> println(testInfo,"Password changed"))
                .onFailure(msg -> println(testInfo,msg.text()));

        // Then
        assertThat(result.hasFailed()).isTrue();
        assertThat(result.error()).isExactlyInstanceOf(UserUpdateFailed.class);
        verify(userRepository).update(user);
    }
}
