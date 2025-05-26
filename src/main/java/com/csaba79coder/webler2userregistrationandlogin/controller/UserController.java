package com.csaba79coder.webler2userregistrationandlogin.controller;

import com.csaba79coder.webler2userregistrationandlogin.model.UserLoginModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserRegistrationModel;
import com.csaba79coder.webler2userregistrationandlogin.model.UserUpdateModel;
import com.csaba79coder.webler2userregistrationandlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserRegistrationModel model) {
        return ResponseEntity.status(201).body(userService.registerUser(model));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> findAllUsers() {
        return ResponseEntity.status(200).body(userService.renderAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserLoginModel userLoginModel) {
        return ResponseEntity.status(200).body(userService.loginUser(userLoginModel));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUserById(@PathVariable Long id, @RequestBody UserUpdateModel userUpdateModel) {
        return ResponseEntity.status(200).body(userService.partialUpdateExistingUser(id, userUpdateModel));
    }
}
