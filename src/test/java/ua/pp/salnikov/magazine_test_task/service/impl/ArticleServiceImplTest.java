package ua.pp.salnikov.magazine_test_task.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.pp.salnikov.magazine_test_task.entity.Article;
import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleRequest;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleResponse;
import ua.pp.salnikov.magazine_test_task.repository.ArticleRepository;
import ua.pp.salnikov.magazine_test_task.service.UserService;
import ua.pp.salnikov.magazine_test_task.util.TestModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void whenCreateArticle_shouldReturnArticleResponse() {
        ArticleRequest articleRequest = TestModel.generateArticleRequest();
        Article article = TestModel.generateArticle();
        ArticleResponse articleResponse = TestModel.generateArticleResponse();
        when(articleRepository.findOne(Example.of(article))).thenReturn(Optional.empty());
        when(userService.getUserById(anyInt())).thenReturn(User.builder().build());
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        assertEquals(articleResponse, articleService.createArticle(articleRequest));
    }

}