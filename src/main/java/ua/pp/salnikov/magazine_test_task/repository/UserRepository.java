package ua.pp.salnikov.magazine_test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.pp.salnikov.magazine_test_task.constants.DBConstants;
import ua.pp.salnikov.magazine_test_task.entity.ArticleColor;
import ua.pp.salnikov.magazine_test_task.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByAgeGreaterThanOrderByIdAsc(Integer age);

    Optional<User> findByName(String name);

    @Query(DBConstants.JPQL_FIND_ALL_USERS_WITH_ARTICLES_WHERE_ARTICLE_COLOR_IS)
    List<User> findAllWithArticlesWhereArticleColorIs(ArticleColor color);

    @Query(value = DBConstants.SQL_FIND_ALL_UNIQUE_USERNAMES_WITH_ARTICLE_COUNT_GREATER_THAN,
            nativeQuery = true)
    List<String> findAllUserNamesWithArticleCountGreaterThan(Integer count);

}
