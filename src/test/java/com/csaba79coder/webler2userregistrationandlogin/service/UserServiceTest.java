package com.csaba79coder.webler2userregistrationandlogin.service;

import com.csaba79coder.webler2userregistrationandlogin.entity.User;
import com.csaba79coder.webler2userregistrationandlogin.model.UserModel;
import com.csaba79coder.webler2userregistrationandlogin.persistence.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

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

        when(userRepository.findAll()).thenReturn(mockUsers);

        // When
        List<UserModel> users = userService.renderAllUsers();

        // Then
        //assertEquals(expectedModels, users);
        List<UserModel> expectedUserModels = List.of(new UserModel(1L, "gabor@gmail.com", "Gabor"));

        assertThat(users)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedUserModels);

        assertThat(users)
                .usingRecursiveComparison()
                .isEqualTo(expectedUserModels);
    }
}