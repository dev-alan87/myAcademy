package io.github.devalan87.myAcademy.exception;

import io.github.devalan87.myAcademy.service.CourseService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class CourseAlreadyRegistered
        extends ResponseStatusException {

    public CourseAlreadyRegistered() {
        super(HttpStatus.BAD_REQUEST, "Course code already registered.");
    }

    public CourseAlreadyRegistered(String error) {
        super(HttpStatus.BAD_REQUEST, "Course code already registered.\n"+error);
    }

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public CourseAlreadyRegistered handleConstraintValueException(CourseAlreadyRegistered ex) {
        return this;
    }

}
