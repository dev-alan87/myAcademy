package io.github.devalan87.myAcademy.error;

import io.github.devalan87.myAcademy.exception.*;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandleError
        extends ResponseEntityExceptionHandler {

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(apiError.toJSon(), headers, apiError.getStatus());
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message("Username or email already registered.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build()
        );
    }

    @ExceptionHandler(InvalidPassordException.class)
    public ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPassordException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build());
    }

    @ExceptionHandler(CourseAlreadyRegistered.class)
    public ResponseEntity<ApiError> handleCourseAlreadyRegisteredException(CourseAlreadyRegistered ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status((HttpStatus) ex.getStatusCode())
                .build());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ApiError> handleCourseNotFoundException(CourseNotFoundException ex) {
        return buildResponseEntity(ApiError.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(ex.getMessage())
                .status((HttpStatus) ex.getStatusCode())
                .build());
    }

}
