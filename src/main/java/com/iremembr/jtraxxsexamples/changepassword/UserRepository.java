package com.iremembr.jtraxxsexamples.changepassword;

import com.iremembr.jtraxxs.ValueResult;
import com.iremembr.jtraxxs.VoidResult;
import com.iremembr.jtraxxsexamples.changepassword.messages.UserNotFound;
import com.iremembr.jtraxxsexamples.changepassword.messages.UserUpdateFailed;

public interface UserRepository {

    ValueResult<User, UserNotFound> find(String username);

    VoidResult<UserUpdateFailed> update(User user);

}
