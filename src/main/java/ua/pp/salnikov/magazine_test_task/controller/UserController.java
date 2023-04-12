package ua.pp.salnikov.magazine_test_task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.salnikov.magazine_test_task.constants.Messages;
import ua.pp.salnikov.magazine_test_task.entity.dto.*;
import ua.pp.salnikov.magazine_test_task.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        log.info(Messages.TRY_LOGIN_USER);
        UserLoginResponse userLoginResponse = userService.loginUser(userLoginRequest);
        log.info(Messages.OK_LOGIN_USER, userLoginResponse.getUserResponse());
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(
            @Valid @RequestBody UserRegisterRequest registerUserRequest) {
        log.info(Messages.TRY_REGISTER_USER);
        UserRegisterResponse registerUserResponse = userService.registerUser(registerUserRequest);
        log.info(Messages.OK_REGISTER_USER, registerUserResponse.getUserResponse());
        return new ResponseEntity<>(registerUserResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info(Messages.TRY_GET_ALL_USERS);
        List<UserResponse> userResponseList = userService.getAllUsers();
        log.info(Messages.OK_GET_ALL_USERS, userResponseList);
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/older-than/{age}")
    public ResponseEntity<List<UserResponse>> getAllUsersOlderThan(@PathVariable("age") Integer age) {
        log.info(Messages.TRY_GET_ALL_USERS_OLDER_THAN, age);
        List<UserResponse> userResponseList = userService.getAllUsersOlderThan(age);
        log.info(Messages.OK_GET_ALL_USERS_OLDER_THAN, age, userResponseList);
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/articles-color/{color}")
    public ResponseEntity<List<UserWithArticlesResponse>> getAllUsersWithArticles(@PathVariable("color") String color) {
        log.info(Messages.TRY_GET_ALL_USERS_WITH_ARTICLES_COLOR, color);
        List<UserWithArticlesResponse> userWithArticlesResponseList =
                userService.getAllUsersWithArticles(color);
        log.info(Messages.OK_GET_ALL_USERS_WITH_ARTICLES_COLOR, color, userWithArticlesResponseList);
        return new ResponseEntity<>(userWithArticlesResponseList, HttpStatus.OK);
    }

    @GetMapping("/articles-count/{count}")
    public ResponseEntity<List<String>> getAllUserNamesWithCountOfArticlesGreaterThan(
            @PathVariable("count") Integer count) {
        log.info(Messages.TRY_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN, count);
        List<String> userNames = userService.getAllUserNamesWithCountOfArticlesGreaterThan(count);
        log.info(Messages.OK_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN, count, userNames);
        return new ResponseEntity<>(userNames, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info(Messages.TRY_CREATE_USER);
        UserResponse createdUserResponse = userService.createUser(userRequest);
        log.info(Messages.OK_CREATE_USER, createdUserResponse);
        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }

}