package ua.pp.salnikov.magazine_test_task.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;

}
