package io.github.devalan87.myAcademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidPassordException
        extends RuntimeException {

    public InvalidPassordException() {
        super("Invalid password.");
    }

    public InvalidPassordException(String error) {
        super("Invalid password.\n" + error);
    }

}
