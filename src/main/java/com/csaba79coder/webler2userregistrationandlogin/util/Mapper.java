package com.csaba79coder.webler2userregistrationandlogin.util;

import com.csaba79coder.webler2userregistrationandlogin.entity.User;

import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserRegistrationModel;

public class Mapper {

    public static UserModel mapUserEntityToUserModel(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setEmail(entity.getEmail());
        model.setUsername(entity.getUsername());
        return model;
    }

    public static User mapUserRegistrationModelToUserEntity(UserRegistrationModel model, String encodedPassword) {
        User user = new User();
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        user.setPassword(encodedPassword);
        return user;
    }

    public static User mapUserModelToUserEntity(UserModel model) {
        User user = new User();
        user.setId(model.getId());
        user.setEmail(model.getEmail());
        user.setUsername(model.getUsername());
        return user;
    }

    private Mapper() {

    }
}
