package com.csaba79coder.webler2userregistrationandlogin.service;

import com.csaba79coder.webler2userregistrationandlogin.entity.User;
import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.persistence.UserRepository;
import com.csaba79coder.webler2userregistrationandlogin.util.Mapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("Given users when find all users then return all users")
    public void givenUsers_whenFindAllUsers_thenReturnAllUsers() {
        // Given
        User user = new User(1L, "gabor@gmail.com", "Gabor", "password");
        List<User> mockUsers = List.of(user);

        List<UserModel> expectedModels = mockUsers.stream()
                .map(Mapper::mapUserEntityToUserModel)
                .toList();

        when(userRepository.findAll()).thenReturn(mockUsers);

        // When
        List<UserModel> users = userService.renderAllUsers();

        // Then
        assertEquals(expectedModels, users);
    }

}