package ua.pp.salnikov.magazine_test_task.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.pp.salnikov.magazine_test_task.entity.ArticleColor;
import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.UserRequest;
import ua.pp.salnikov.magazine_test_task.entity.dto.UserResponse;
import ua.pp.salnikov.magazine_test_task.entity.dto.UserWithArticlesResponse;
import ua.pp.salnikov.magazine_test_task.repository.UserRepository;
import ua.pp.salnikov.magazine_test_task.util.TestModel;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void whenGetAllUsers_shouldReturnListOfAllUsers() {
        List<UserResponse> userResponseList = TestModel.generateListOfUserResponses();
        List<User> users = TestModel.generateListOfUsers();
        when(userRepository.findAll()).thenReturn(users);
        assertIterableEquals(userResponseList, userService.getAllUsers());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void whenGetAllUsersWithArticlesOfColor_shouldReturnListOfAllUsersWithArticlesOfColor() {
        List<UserWithArticlesResponse> userResponseList = TestModel.generateListOfUserWithArticlesResponses();
        List<User> users = TestModel.generateListOfUsersWithArticles();
        when(userRepository.findAllWithArticlesWhereArticleColorIs(ArticleColor.BLUE)).thenReturn(users);
        assertIterableEquals(userResponseList, userService.getAllUsersWithArticles("blue"));
        verify(userRepository, times(1)).findAllWithArticlesWhereArticleColorIs(ArticleColor.BLUE);
    }

    @Test
    void whenGetAllUserNamesWithCountOfArticlesGreaterThan3_shouldReturnListOfAllUserNamesWithArticlesCountGreaterThan3() {
        List<String> userNames = Arrays.asList("Nestor Mahno", "Taras Shevchenko");
        when(userRepository.findAllUserNamesWithArticleCountGreaterThan(anyInt())).thenReturn(userNames);
        assertIterableEquals(userNames, userService.getAllUserNamesWithCountOfArticlesGreaterThan(anyInt()));
        verify(userRepository, times(1)).findAllUserNamesWithArticleCountGreaterThan(anyInt());
    }

    @Test
    void whenCreateUser_shouldReturnUserResponse() {
        UserRequest userRequest = TestModel.generateUserRequest();
        User user = TestModel.generateUser();
        UserResponse userResponse = TestModel.generateUserResponse();
        when(userRepository.findOne(Example.of(user))).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("someword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        assertEquals(userResponse, userService.createUser(userRequest));
    }

    @Test
    void whenGetUserById_shouldReturnUser() {
        User user = TestModel.generateUser();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserById(1));
        verify(userRepository, times(1)).findById(anyInt());
    }

}