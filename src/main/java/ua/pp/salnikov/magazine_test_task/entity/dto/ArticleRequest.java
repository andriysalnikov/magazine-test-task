package ua.pp.salnikov.magazine_test_task.entity.dto;

import lombok.*;
import ua.pp.salnikov.magazine_test_task.entity.Article;
import ua.pp.salnikov.magazine_test_task.entity.ArticleColor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ArticleRequest {

    @NotNull(message = "Missing 'text' field")
    @NotBlank(message = "Article 'text' can not be empty")
    private String text;

    @NotNull(message = "Missing 'color' field")
    @NotBlank(message = "Article 'color' can not be empty")
    private String color;

    @NotNull(message = "Missing correct 'userId' field")
    private Integer userId;

    public static Article toArticle(ArticleRequest articleRequest) {
        return Article.builder()
                .text(articleRequest.getText())
                .color(ArticleColor.valueOf(articleRequest.getColor().toUpperCase()))
                .build();
    }

}
