package ua.pp.salnikov.magazine_test_task.service;

import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleRequest;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleResponse;

public interface ArticleService {

    ArticleResponse createArticle(ArticleRequest articleRequest);

}