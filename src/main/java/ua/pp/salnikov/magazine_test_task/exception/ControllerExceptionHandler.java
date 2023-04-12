package ua.pp.salnikov.magazine_test_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ua.pp.salnikov.magazine_test_task.exception.custom.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(errors.get(0))
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(ArticleAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> articleAlreadyExistException(
            ArticleAlreadyExistException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(ArticleCannotCreateException.class)
    public ResponseEntity<ErrorMessage> articleCannotCreateException(
            ArticleCannotCreateException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> userAlreadyExistException(
            UserAlreadyExistException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(UserCannotCreateException.class)
    public ResponseEntity<ErrorMessage> userCannotCreateException(
            UserCannotCreateException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(UserCannotLoginException.class)
    public ResponseEntity<ErrorMessage> userCannotLoginException(
            UserCannotLoginException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(
            UserNotFoundException exception, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder()
                .statusCode(BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
        return new ResponseEntity<>(message, BAD_REQUEST);
    }

}


