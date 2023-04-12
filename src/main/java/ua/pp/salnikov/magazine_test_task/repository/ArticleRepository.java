package ua.pp.salnikov.magazine_test_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pp.salnikov.magazine_test_task.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
