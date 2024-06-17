package dev.levimaciell.chatAPI.exceptions;

import dev.levimaciell.chatAPI.user.exceptions.UserCreationValidationException;
import dev.levimaciell.chatAPI.user.exceptions.UserDeletionException;
import dev.levimaciell.chatAPI.user.exceptions.UserUpdateValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    // When bean validation captures an error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDtoMultipleErrors>
    error400MethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req){

        var errors = ex.getFieldErrors()
                .stream()
                .map(e -> new ErrorField(e.getField(), e.getDefaultMessage()))
                .toList();

        var response = new ErrorDtoMultipleErrors(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error while creating a new user",
                req.getServletPath(),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserCreationValidationException.class)
    public ResponseEntity<ErrorDto> error409UserCreationValidationException
            (UserCreationValidationException ex, HttpServletRequest req){

        HttpStatus statusCode = HttpStatus.CONFLICT;

        var errorResponse = buildErrorDto(ex, req, statusCode);
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(UserDeletionException.class)
    public ResponseEntity<ErrorDto> error406UserDeletionException
            (UserDeletionException ex, HttpServletRequest req){

        HttpStatus statusCode = HttpStatus.NOT_ACCEPTABLE;

        var errorResponse = buildErrorDto(ex, req, statusCode);
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(UserUpdateValidationException.class)
    public ResponseEntity<ErrorDto> error400UserUpdateValidationException
            (UserUpdateValidationException ex, HttpServletRequest req){

        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        var errorResponse = buildErrorDto(ex, req, statusCode);
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    private ErrorDto buildErrorDto(Exception ex, HttpServletRequest req, HttpStatus status){
        return new ErrorDto(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                ex.getMessage(),
                req.getServletPath()

        );

    }



}
