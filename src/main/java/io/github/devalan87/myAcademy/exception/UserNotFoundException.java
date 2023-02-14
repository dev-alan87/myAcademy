package io.github.devalan87.myAcademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserNotFoundException
        extends RuntimeException {

    public UserNotFoundException() {
        super("User not found. Invalid login or password.");
    }

    public UserNotFoundException(String message) {
        super("User not found. Invalid login or password.\n"+message);
    }

}
