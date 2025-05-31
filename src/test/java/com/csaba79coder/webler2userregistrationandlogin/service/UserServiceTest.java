package com.csaba79coder.webler2userregistrationandlogin.service;

import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.persistence.UserRepository;
import com.csaba79coder.webler2userregistrationandlogin.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given users when find all users then return all users")
    public void givenUsers_whenFindAllUsers_thenReturnAllUsers() {
        //Given
        List<UserModel> mockUsers = List.of(
                new UserModel(1L, "gabor@gmail.com", "Gabor")
        );
        //When
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserModel> users = userService.renderAllUsers();
        //Then
        assertEquals(mockUsers, users);
    }

}