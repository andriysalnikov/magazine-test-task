package ua.pp.salnikov.magazine_test_task.constants;

public final class Messages {

    public static final String TRY_GET_ALL_USERS =
            "Trying to get all 'Users'";

    public static final String OK_GET_ALL_USERS =
            "All 'Users' obtained: {}";

    public static final String TRY_GET_ALL_USERS_OLDER_THAN =
            "Trying to get all 'Users' older than {}";

    public static final String OK_GET_ALL_USERS_OLDER_THAN =
            "All 'Users' older than {} obtained: {}";

    public static final String TRY_GET_ALL_USERS_WITH_ARTICLES_COLOR =
            "Trying to get all 'Users' with 'Articles' color {}";

    public static final String OK_GET_ALL_USERS_WITH_ARTICLES_COLOR =
            "All 'Users' with 'Articles' color {} obtained: {}";

    public static final String TRY_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN =
            "Trying to get all 'UserNames' with 'Article' count greater than {}";

    public static final String OK_GET_ALL_UNIQUE_USERSNAMES_WITH_ARTICLE_COUNT_GREATER_THAN =
            "All 'UserNames' with 'Article' count greater than {} obtained: {}";

    public static final String TRY_CREATE_USER =
            "Trying to create 'User'";

    public static final String OK_CREATE_USER =
            "'User' created: {}";

    public static final String ERROR_CREATE_USER =
            "Can not create 'User'";

    public static final String TRY_GET_USER_BY_ID =
            "Trying to obtain 'User' by id: {}";

    public static final String OK_GET_USER_BY_ID =
            "'User' with id {} obtained: {}";

    public static final String ERROR_GET_USER_BY_ID =
            "Can not find 'User' by id";

    public static final String TRY_CREATE_ARTICLE =
            "Trying to create 'Article'";

    public static final String OK_CREATE_ARTICLE =
            "'Article' created: {}";

    public static final String ERROR_CREATE_ARTICLE =
            "Can not create 'Article'";

    public static final String TRY_LOGIN_USER =
            "'User' trying to log in";

    public static final String OK_LOGIN_USER =
            "'User' logged in: {}";

    public static final String ERROR_GET_USER_BY_NAME =
            "Can not find 'User' by name";

    public static final String TRY_REGISTER_USER =
            "'User' trying to register";

    public static final String OK_REGISTER_USER =
            "'User' registered: {}";

    public static final String ERROR_USER_ALREADY_EXIST =
            "'User' with this 'name' already exists";

    public static final String ERROR_ARTICLE_ALREADY_EXIST =
            "'Article' with this 'text' already exists";

    private Messages() {}

}
