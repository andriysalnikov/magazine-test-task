package ua.pp.salnikov.magazine_test_task.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pp.salnikov.magazine_test_task.constants.Messages;
import ua.pp.salnikov.magazine_test_task.entity.ArticleColor;
import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.*;
import ua.pp.salnikov.magazine_test_task.exception.custom.UserAlreadyExistException;
import ua.pp.salnikov.magazine_test_task.exception.custom.UserCannotCreateException;
import ua.pp.salnikov.magazine_test_task.exception.custom.UserCannotLoginException;
import ua.pp.salnikov.magazine_test_task.exception.custom.UserNotFoundException;
import ua.pp.salnikov.magazine_test_task.repository.UserRepository;
import ua.pp.salnikov.magazine_test_task.security.JwtProvider;
import ua.pp.salnikov.magazine_test_task.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        log.debug(Messages.TRY_REGISTER_USER);
        userRepository.findOne(Example.of(User.builder().name(userRegisterRequest.getName()).build()))
                .ifPresent(article -> {
                    log.error(Messages.ERROR_USER_ALREADY_EXIST);
                    throw new UserAlreadyExistException(Messages.ERROR_USER_ALREADY_EXIST);
                });
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User createdUser;
        try {
            createdUser = userRepository.save(UserRegisterRequest.toUser(userRegisterRequest));
        } catch (RuntimeException exception) {
            log.error(Messages.ERROR_CREATE_USER);
            throw new UserCannotCreateException(Messages.ERROR_CREATE_USER);
        }
        String token = jwtProvider.generateToken(createdUser);
        log.debug(Messages.OK_REGISTER_USER, createdUser);
        return UserRegisterResponse.fromUser(token, createdUser);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        log.debug(Messages.TRY_LOGIN_USER);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.getName(), userLoginRequest.getPassword()));
        User user = userRepository.findByName(userLoginRequest.getName()).orElseThrow(() -> {
            log.error(Messages.ERROR_GET_USER_BY_NAME);
            throw new UserCannotLoginException(Messages.ERROR_GET_USER_BY_NAME);
        });
        String token = jwtProvider.generateToken(user);
        log.debug(Messages.OK_LOGIN_USER, user);
        return UserLoginResponse.fromUser(token, user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.debug(Messages.TRY_GET_ALL_USERS);
        List<User> users = userRepository.findAll();
        log.debug(Messages.OK_GET_ALL_USERS, users);
        return users.stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllUsersOlderThan(Integer age) {
        log.debug(Messages.TRY_GET_ALL_USERS_OLDER_THAN, age);
        List<User> users = userRepository.findAllByAgeGreaterThanOrderByIdAsc(age);
        log.debug(Messages.OK_GET_ALL_USERS_OLDER_THAN, age, users);
        return users.stream()
                .map(UserResponse::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserWithArticlesResponse> getAllUsersWithArticles(String color) {
        log.debug(Messages.TRY_GET_ALL_USERS_WITH_ARTICLES_COLOR, color);
        ArticleColor articleColor = ArticleColor.valueOf(color.toUpperCase());
        List<User> users = userRepository.findAllWithArticlesWhereArticleColorIs(articleColor);
        log.debug(Messages.OK_GET_ALL_USERS_WITH_ARTICLES_COLOR, color, users);
        return users.stream()
                .map(UserWithArticlesResponse::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllUserNamesWithCountOfArticlesGreaterThan(Integer count) {
        log.debug(Messages.TRY_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN, count);
        List<String> userNames = userRepository.findAllUserNamesWithArticleCountGreaterThan(count);
        log.debug(Messages.OK_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN, count, userNames);
        return userNames;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        log.debug(Messages.TRY_CREATE_USER);
        userRepository.findOne(Example.of(User.builder().name(userRequest.getName()).build()))
                .ifPresent(article -> {
                    log.error(Messages.ERROR_USER_ALREADY_EXIST);
                    throw new UserAlreadyExistException(Messages.ERROR_USER_ALREADY_EXIST);
                });
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User createdUser;
        try {
            createdUser = userRepository.save(UserRequest.toUser(userRequest));
        } catch (RuntimeException exception) {
            log.error(Messages.ERROR_CREATE_USER);
            throw new UserCannotCreateException(Messages.ERROR_CREATE_USER);
        }
        log.debug(Messages.OK_CREATE_USER, createdUser);
        return UserResponse.fromUser(createdUser);
    }

    @Override
    public User getUserById(Integer id) {
        log.debug(Messages.TRY_GET_USER_BY_ID, id);
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.ERROR_GET_USER_BY_ID);
            throw new UserNotFoundException(Messages.ERROR_GET_USER_BY_ID);
        });
        log.debug(Messages.OK_GET_USER_BY_ID, id, user);
        return user;
    }

}