package ua.pp.salnikov.magazine_test_task.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleAlreadyExistException extends RuntimeException {

    private String message;

}
