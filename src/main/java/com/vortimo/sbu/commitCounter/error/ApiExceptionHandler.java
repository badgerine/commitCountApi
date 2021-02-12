package com.vortimo.sbu.commitCounter.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({RepositoryNotFoundException.class, RuntimeException.class})
    public final ResponseEntity<Object> handleUserNotFoundException(RepositoryNotFoundException ex, WebRequest request) {
        List<String> subErrors = new ArrayList<>();
        subErrors.add(ex.getLocalizedMessage());
        ApiError error = new ApiError("Record Not Found", subErrors);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
