package ua.pp.salnikov.magazine_test_task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.salnikov.magazine_test_task.constants.Messages;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleRequest;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleResponse;
import ua.pp.salnikov.magazine_test_task.service.ArticleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        log.info(Messages.TRY_CREATE_ARTICLE);
        ArticleResponse createdArticleResponse = articleService.createArticle(articleRequest);
        log.info(Messages.OK_CREATE_ARTICLE, createdArticleResponse);
        return new ResponseEntity<>(createdArticleResponse, HttpStatus.CREATED);
    }

}