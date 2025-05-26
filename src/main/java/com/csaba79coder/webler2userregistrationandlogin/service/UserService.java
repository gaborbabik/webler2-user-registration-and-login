package com.csaba79coder.webler2userregistrationandlogin.service;

import com.csaba79coder.webler2userregistrationandlogin.controller.exception.UserAlreadyExistsException;
import com.csaba79coder.webler2userregistrationandlogin.entity.User;
import com.csaba79coder.webler2userregistrationandlogin.model.UserLoginModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserRegistrationModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserUpdateModel;
import com.csaba79coder.webler2userregistrationandlogin.persistence.UserRepository;
import com.csaba79coder.webler2userregistrationandlogin.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.csaba79coder.webler2userregistrationandlogin.util.Mapper.*;
import static com.csaba79coder.webler2userregistrationandlogin.util.PasswordEncoder.checkPassword;
import static com.csaba79coder.webler2userregistrationandlogin.util.PasswordEncoder.hashPassword;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserModel registerUser(UserRegistrationModel registrationModel) {
        UserModel userModel = findUserByEmail(registrationModel.getEmail());
        if (userModel != null) {
            String message = String.format("User with email %s already exists", registrationModel.getEmail());
            log.info(message);
            throw new UserAlreadyExistsException(message);
        } else {
            if (registrationModel.getPassword().isBlank() || registrationModel.getRepeatedPassword().isBlank() ||
                    registrationModel.getEmail().isBlank()) {
                String message = "Invalid input";
                log.info(message);
                throw new InputMismatchException(message);
            } else if (registrationModel.getUsername().isBlank()) {
                String message = "Invalid input";
                log.info(message);
                throw new InputMismatchException(message);
            } else {
                if (!registrationModel.getPassword().equals(registrationModel.getRepeatedPassword())) {
                    String message = "Invalid input";
                    log.info(message);
                    throw new InputMismatchException(message);
                } else {
                    return mapUserEntityToUserModel(
                            userRepository.save(mapUserRegistrationModelToUserEntity(
                                    registrationModel, hashPassword(registrationModel.getPassword()))));
                }
            }
        }
    }

    public List<UserModel> renderAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::mapUserEntityToUserModel)
                .collect(Collectors.toList());
    }

    public UserModel loginUser(UserLoginModel loginModel) {
        Optional<User> existingUser = userRepository.findUserByEmail(loginModel.getEmail());
        if (existingUser.isEmpty()) {
            String message = String.format("User with email %s not found", loginModel.getEmail());
            log.info(message);
            throw new NoSuchElementException(message);
        }
        boolean isPasswordMatches = checkPassword(loginModel.getPassword(), existingUser.get().getPassword());
        if (!isPasswordMatches) {
            String message = "Invalid input";
            log.info(message);
            throw new InputMismatchException(message);
        }
        return mapUserEntityToUserModel(existingUser.get());
    }

    public void deleteUserById(Long id) {
        Optional<User> existingUser = Optional.of(mapUserModelToUserEntity(findUserById(id)));
        userRepository.deleteById(existingUser.get().getId());
    }

    public UserModel partialUpdateExistingUser(Long id, UserUpdateModel userUpdateModel) {
        Optional<User> existingUser = Optional.of(mapUserModelToUserEntity(findUserById(id)));
        if (userUpdateModel.getUsername() != null && !userUpdateModel.getUsername().isBlank()) {
            existingUser.get().setUsername(userUpdateModel.getUsername());
        }
        if (userUpdateModel.getPassword() != null && !userUpdateModel.getPassword().isBlank() &&
                userUpdateModel.getRepeatedPassword() != null && !userUpdateModel.getRepeatedPassword().isBlank()
                && userUpdateModel.getRepeatedPassword().equals(userUpdateModel.getPassword())) {
            existingUser.get().setPassword(hashPassword(userUpdateModel.getPassword()));
        }
        return mapUserEntityToUserModel(userRepository.save(existingUser.get()));
    }

    private UserModel findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser.map(Mapper::mapUserEntityToUserModel).orElse(null);
    }

    private UserModel findUserById(Long id) {
        return mapUserEntityToUserModel(userRepository.findUserById(id)
                .orElseThrow(() -> {
                    String message = String.format("User with id %d not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                }));
    }
}
