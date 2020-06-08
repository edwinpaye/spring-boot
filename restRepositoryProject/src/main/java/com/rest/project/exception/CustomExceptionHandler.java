package com.rest.project.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({PersistenceException.class, ConstraintViolationException.class})
//    public String handlePersistenceException(PersistenceException e){
////        log.error("Handling persistence exception. "+e.getLocalizedMessage());
////        Map<String, String> errors = new HashMap<>();
////        e.getConstraintViolations().forEach(error -> {
////            errors.put(error.getPropertyPath().toString(), error.getMessage());
////        });
//        if (e.getCause() instanceof ConstraintViolationException){
////            return ((ConstraintViolationException) e.getCause()).getConstraintName();
//            return e.getCause().getLocalizedMessage();
//        }
//        return e.getMessage();
//    }

    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public String handleAccessDeniedException(Exception ex, WebRequest request){
        RepositoryConstraintViolationException violationException = (RepositoryConstraintViolationException) ex;
        String errors = violationException.getErrors().getAllErrors().stream()
                .map(objectError -> objectError.toString()).collect(Collectors.joining("\n"));
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionMessage unexpectedException(HttpServletRequest req, Exception e){
        return new ExceptionMessage(new Date(), e.getMessage(), req.getRequestURI(), e.getCause().getLocalizedMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
