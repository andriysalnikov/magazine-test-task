package ua.pp.salnikov.magazine_test_task.service;

import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.*;

import java.util.List;

public interface UserService {

    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);

    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);

    List<UserResponse> getAllUsers();

    User getUserById(Integer id);

    List<UserResponse> getAllUsersOlderThan(Integer age);

    List<UserWithArticlesResponse> getAllUsersWithArticles(String color);

    List<String> getAllUserNamesWithCountOfArticlesGreaterThan(Integer count);

    UserResponse createUser(UserRequest userRequest);

}