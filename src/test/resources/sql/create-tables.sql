DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(60) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text CLOB NOT NULL,
    color VARCHAR(10) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (name, password, age)
    VALUES
        ('Bogdan Stupka'    , '$2a$10$FNAE7jQVKRe6lqR2ho7pwu8o8TWPIuMkZTvbkdxrjAczWM7QsqqKu', 70), -- pwd: one
        ('Taras Shevchenko' , '$2a$10$Bpy7ic1lVq7xMnnFKI0u1.JPe3BeKNgvKt5xXIngUSQ6IrdJYwOv6', 55), -- pwd: six
        ('Nestor Mahno'     , '$2a$10$BJeKTRs1Uvr2LQEap.uQJOGcEEblGJD82ZCrhepTxJ/kWaVFszEY.', 28); -- pwd: seven

INSERT INTO articles (text, color, user_id)
    VALUES
        ('there are many variations of passages',                   'RED',      1),
        ('of Lorem Ipsum available',                                'ORANGE',   1),
        ('but the majority have suffered',                          'YELLOW',   2),
        ('alteration in some form',                                 'GREEN',    2),
        ('by injected humour, or randomised words',                 'BLUE',     2),
        ('which don''t look even slightly believable',              'INDIGO',   2),
        ('if you are going to use a passage of Lorem Ipsum',        'VIOLET',   2),
        ('you need to be sure there isn''t anything embarrassing',  'RED',      3),
        ('hidden in the middle of text',                            'ORANGE',   3),
        ('all the Lorem Ipsum generators on the Internet',          'GREEN',    3),
        ('tend to repeat predefined chunks as necessary',           'BLUE',     3);