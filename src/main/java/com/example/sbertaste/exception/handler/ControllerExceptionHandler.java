package com.example.sbertaste.exception.handler;

import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String VALIDATION_ERROR = "Validation error";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorPrettyView> constraintViolationExceptionHandler(@NotNull ConstraintViolationException exception) {
                return error(VALIDATION_ERROR, getErrorDetails(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPrettyView> validationErrorHandler(@NotNull MethodArgumentNotValidException exception) {
        return error(VALIDATION_ERROR, getErrorDetails(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, STNotFoundException.class})
    public ResponseEntity<ErrorPrettyView> handleNotFoundException(@NotNull Exception exception) {
        return error(exception, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(STCartEmptyException.class)
    public ResponseEntity<ErrorPrettyView> handleSTCartEmptyException(@NotNull Exception exception) {
        return error(exception, HttpStatus.CONFLICT);
    }

    private ResponseEntity<ErrorPrettyView> error(Exception exception, HttpStatus httpStatus) {
        String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return error(message, httpStatus);
    }

    private ResponseEntity<ErrorPrettyView> error(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorPrettyView(message), httpStatus);
    }

    private ResponseEntity<ErrorPrettyView> error(String message, List<String> details, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorPrettyView(message, details), httpStatus);
    }

    private List<String> getErrorDetails(ConstraintViolationException exception) {
        return List.of(exception.getSQLException().getMessage());
    }

    private List<String> getErrorDetails(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors.entrySet().stream()
                .map(entry -> String.format("%s: %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
