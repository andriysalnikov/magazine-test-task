package ua.pp.salnikov.magazine_test_task.constants;

public final class DBConstants {

    public static final String JPQL_FIND_ALL_USERS_WITH_ARTICLES_WHERE_ARTICLE_COLOR_IS =
            "SELECT DISTINCT u FROM User u JOIN FETCH u.articles a WHERE a.color = :color";

    public static final String SQL_FIND_ALL_UNIQUE_USERNAMES_WITH_ARTICLE_COUNT_GREATER_THAN =
            "SELECT name FROM (" +
                    "SELECT users.name, COUNT(*) as count FROM users " +
                    "JOIN articles ON users.id = articles.user_id " +
                    "GROUP BY users.name " +
                    "HAVING count > :count) " +
                "ORDER BY name";

    private DBConstants() {}

}