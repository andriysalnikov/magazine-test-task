package ua.pp.salnikov.magazine_test_task.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ArticleColor {

    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet");

    public final String color;

}
