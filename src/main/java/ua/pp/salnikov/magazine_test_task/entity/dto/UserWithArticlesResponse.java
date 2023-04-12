package ua.pp.salnikov.magazine_test_task.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pp.salnikov.magazine_test_task.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserWithArticlesResponse {

    private Integer id;
    private String name;
    private Integer age;
    private List<ArticleResponse> articles;

    public static UserWithArticlesResponse fromUser(User user) {
        return UserWithArticlesResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .articles(user.getArticles().stream()
                        .map(ArticleResponse::fromArticle).collect(Collectors.toList()))
                .build();
    }

}
