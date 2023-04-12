package ua.pp.salnikov.magazine_test_task.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ua.pp.salnikov.magazine_test_task.constants.Messages;
import ua.pp.salnikov.magazine_test_task.entity.Article;
import ua.pp.salnikov.magazine_test_task.entity.User;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleRequest;
import ua.pp.salnikov.magazine_test_task.entity.dto.ArticleResponse;
import ua.pp.salnikov.magazine_test_task.exception.custom.ArticleAlreadyExistException;
import ua.pp.salnikov.magazine_test_task.exception.custom.ArticleCannotCreateException;
import ua.pp.salnikov.magazine_test_task.repository.ArticleRepository;
import ua.pp.salnikov.magazine_test_task.service.ArticleService;
import ua.pp.salnikov.magazine_test_task.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        log.debug(Messages.TRY_CREATE_ARTICLE);
        articleRepository.findOne(Example.of(Article.builder().text(articleRequest.getText()).build()))
                .ifPresent(article -> {
                    log.error(Messages.ERROR_ARTICLE_ALREADY_EXIST);
                    throw new ArticleAlreadyExistException(Messages.ERROR_ARTICLE_ALREADY_EXIST);
                });
        User user = userService.getUserById(articleRequest.getUserId());
        Article article = ArticleRequest.toArticle(articleRequest);
        article.setUser(user);
        Article createdArticle;
        try {
            createdArticle = articleRepository.save(article);
        } catch (RuntimeException exception) {
            log.error(Messages.ERROR_CREATE_ARTICLE);
            throw new ArticleCannotCreateException(Messages.ERROR_CREATE_ARTICLE);
        }
        log.debug(Messages.OK_CREATE_ARTICLE, createdArticle);
        return ArticleResponse.fromArticle(createdArticle);
    }

}