INSERT INTO users (name, password, age)
    VALUES
        ('Bogdan Stupka'    , '$2a$10$FNAE7jQVKRe6lqR2ho7pwu8o8TWPIuMkZTvbkdxrjAczWM7QsqqKu', 70), -- pwd: one
        ('Taras Shevchenko' , '$2a$10$TEAS5/.SEf7gDajy6tonru4gn9Vrt6GbjQ01KZQp/gBXC8.zJ6gfe', 50), -- pwd: two
        ('Yurij Gevz'       , '$2a$10$5q7T9k0Up6U2Bu7a7XB86.USO2tN3Oiq7.iEQhmE7ay8nGBdlDLZK', 12), -- pwd: three
        ('Stas Boklan'      , '$2a$10$7M9F7wzkAZE8UZB6eckfgupI.c5ZV0GKP.0GpGm3vfwnrPnqVp7US', 48), -- pwd: four
        ('Marichka Padalka' , '$2a$10$C.ulOub3ZfVaf3yWbR7F1OH35I.Uij6pECYIwm5JO6fevGri9okvK', 30), -- pwd: five
        ('Juergen Klinsman' , '$2a$10$Bpy7ic1lVq7xMnnFKI0u1.JPe3BeKNgvKt5xXIngUSQ6IrdJYwOv6', 55), -- pwd: six
        ('Nestor Mahno'     , '$2a$10$BJeKTRs1Uvr2LQEap.uQJOGcEEblGJD82ZCrhepTxJ/kWaVFszEY.', 28); -- pwd: seven

INSERT INTO articles (text, color, user_id)
    VALUES
        ('there are many variations of passages',                   'RED',      5),
        ('of Lorem Ipsum available',                                'ORANGE',   4),
        ('but the majority have suffered',                          'YELLOW',   3),
        ('alteration in some form',                                 'GREEN',    2),
        ('by injected humour, or randomised words',                 'BLUE',     1),
        ('which don''t look even slightly believable',              'INDIGO',   7),
        ('if you are going to use a passage of Lorem Ipsum',        'VIOLET',   6),
        ('you need to be sure there isn''t anything embarrassing',  'RED',      5),
        ('hidden in the middle of text',                            'ORANGE',   4),
        ('all the Lorem Ipsum generators on the Internet',          'GREEN',    3),
        ('tend to repeat predefined chunks as necessary',           'BLUE',     2),
        ('making this the first true generator on the Internet',    'INDIGO',   1),
        ('it uses a dictionary of over 200 Latin words',            'VIOLET',   7),
        ('combined with a handful of model sentence structures',    'RED',      6),
        ('to generate Lorem Ipsum which looks reasonable',          'YELLOW',   5),
        ('the generated Lorem Ipsum is therefore',                  'ORANGE',   4),
        ('always free from repetition',                             'GREEN',    3),
        ('injected humour',                                         'BLUE',     2),
        ('or non-characteristic words etc',                         'YELLOW',   1);