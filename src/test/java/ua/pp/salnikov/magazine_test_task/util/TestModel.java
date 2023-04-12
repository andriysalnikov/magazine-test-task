package ua.pp.salnikov.magazine_test_task.util;

import ua.pp.salnikov.magazine_test_task.entity.Article;
import ua.pp.salnikov.magazine_test_task.entity.ArticleColor;
import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestModel {

    public static User generateUser() {
        return User.builder()
                .id(1)
                .name("Bogdan Stupka")
                .password("someword")
                .age(70)
                .build();
    }

    public static UserRequest generateUserRequest() {
        return UserRequest.builder()
                .name("Bogdan Stupka")
                .password("someword")
                .age(70)
                .build();
    }

    public static UserResponse generateUserResponse() {
        return UserResponse.builder()
                .id(1)
                .name("Bogdan Stupka")
                .age(70)
                .build();
    }

    public static List<UserResponse> generateListOfUserResponses() {
        UserResponse userResponse1 = UserResponse.builder().id(1).name("Bogdan Stupka").age(70).build();
        UserResponse userResponse2 = UserResponse.builder().id(2).name("Taras Shevchenko").age(55).build();
        UserResponse userResponse3 = UserResponse.builder().id(3).name("Nestor Mahno").age(28).build();
        return Arrays.asList(userResponse1, userResponse2, userResponse3);
    }

    public static List<User> generateListOfUsers() {
        User user1 = User.builder().id(1).name("Bogdan Stupka").password("someword").age(70).build();
        User user2 = User.builder().id(2).name("Taras Shevchenko").password("someword").age(55).build();
        User user3 = User.builder().id(3).name("Nestor Mahno").password("someword").age(28).build();
        return Arrays.asList(user1, user2, user3);
    }

    public static List<UserWithArticlesResponse> generateListOfUserWithArticlesResponses() {
        UserWithArticlesResponse userResponse2 = UserWithArticlesResponse.builder()
                .id(2)
                .name("Taras Shevchenko")
                .age(55).articles(new ArrayList<>())
                .build();
        UserWithArticlesResponse userResponse3 = UserWithArticlesResponse.builder()
                .id(3)
                .name("Nestor Mahno")
                .age(28).articles(new ArrayList<>()).build();
        userResponse2.getArticles().add(ArticleResponse.builder()
                .id(5)
                .text("by injected humour, or randomised words")
                .color("blue")
                .build());
        userResponse3.getArticles().add(ArticleResponse.builder()
                .id(11)
                .text("tend to repeat predefined chunks as necessary")
                .color("blue")
                .build());
        return Arrays.asList(userResponse2, userResponse3);
    }

    public static List<User> generateListOfUsersWithArticles() {
        User user2 = User.builder()
                .id(2)
                .name("Taras Shevchenko")
                .age(55).password("someword")
                .articles(new ArrayList<>())
                .build();
        User user3 = User.builder()
                .id(3).name("Nestor Mahno")
                .age(28)
                .password("someword")
                .articles(new ArrayList<>())
                .build();
        user2.getArticles().add(Article.builder()
                .id(5)
                .text("by injected humour, or randomised words")
                .color(ArticleColor.BLUE)
                .build());
        user3.getArticles().add(Article.builder()
                .id(11)
                .text("tend to repeat predefined chunks as necessary")
                .color(ArticleColor.BLUE)
                .build());
        return Arrays.asList(user2, user3);
    }

    public static Article generateArticle() {
        return Article.builder()
                .id(1)
                .text("Si vi pacem, para bellum")
                .color(ArticleColor.BLUE)
                .build();
    }

    public static ArticleRequest generateArticleRequest() {
        return ArticleRequest.builder()
                .text("Si vi pacem, para bellum")
                .color("blue")
                .userId(2)
                .build();
    }

    public static ArticleResponse generateArticleResponse() {
        return ArticleResponse.builder()
                .id(1)
                .text("Si vi pacem, para bellum")
                .color("blue")
                .build();
    }

    private TestModel() {}

}
