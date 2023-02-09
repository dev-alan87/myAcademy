package io.github.devalan87.myAcademy.exception;

import io.github.devalan87.myAcademy.error.ApiError;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserAlreadyRegisteredException
        extends RuntimeException {

    public UserAlreadyRegisteredException() {
        super("Username or email already registered.");
    }

    public UserAlreadyRegisteredException(String message) {
        super("Username or email already registered.\n"+message);
    }

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public UserAlreadyRegisteredException handleConstraintValueException(UserAlreadyRegisteredException ex) {
        return this;
    }

}
