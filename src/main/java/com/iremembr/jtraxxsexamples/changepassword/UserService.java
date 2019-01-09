package com.iremembr.jtraxxsexamples.changepassword;

import com.iremembr.jtraxxs.ValueResult;
import com.iremembr.jtraxxs.VoidResult;
import com.iremembr.jtraxxsexamples.changepassword.messages.BadPassword;
import com.iremembr.jtraxxsexamples.changepassword.messages.Message;
import com.iremembr.jtraxxsexamples.changepassword.messages.WrongPassword;

import static com.iremembr.jtraxxs.ValueResult.fail;
import static com.iremembr.jtraxxs.ValueResult.ok;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public VoidResult<Message> changePassword(String username, String oldPassword, String newPassword) {

        ValueResult<User, Message> user = validatePassword(newPassword)
                .castError(Message.class)
                .take(() -> userRepository.find(username))
                .ensure(u -> u.isPassword(oldPassword), new WrongPassword());

        return user
                .onSuccess(u -> u.changePassword(newPassword))
                .ensure(userRepository::update)    // durch take ersetzen
                .toVoidResult();
    }

    private ValueResult<String, BadPassword> validatePassword(String newPassword) {
        return newPassword != null && newPassword.length() > 7
                ? ok(newPassword)
                : fail(new BadPassword());
    }
}
