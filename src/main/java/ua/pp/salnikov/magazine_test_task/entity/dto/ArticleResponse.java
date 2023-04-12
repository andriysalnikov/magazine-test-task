package ua.pp.salnikov.magazine_test_task.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.pp.salnikov.magazine_test_task.entity.Article;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ArticleResponse {

    private Integer id;
    private String text;
    private String color;

    public static ArticleResponse fromArticle(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .text(article.getText())
                .color(article.getColor().color)
                .build();
    }

}
