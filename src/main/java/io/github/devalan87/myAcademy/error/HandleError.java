package io.github.devalan87.myAcademy.error;

import io.github.devalan87.myAcademy.exception.InvalidPassordException;
import io.github.devalan87.myAcademy.exception.UserAlreadyRegisteredException;
import io.github.devalan87.myAcademy.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandleError
        extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError.toJSon(), apiError.getStatus());
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build());
    }

    @ExceptionHandler(InvalidPassordException.class)
    public ResponseEntity handleInvalidPasswordException(InvalidPassordException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build());
    }

}
