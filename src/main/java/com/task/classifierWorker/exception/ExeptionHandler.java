package com.task.classifierWorker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
public class ExeptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ElementExistException.class)
    protected ResponseEntity<Object> handlUserExist(ElementExistException ex) {
        AppError appError = new AppError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(appError);
    }

    @ExceptionHandler(NoSuchEntryException.class)
    protected ResponseEntity<Object> handlNoSuchElement(NoSuchEntryException ex) {
        AppError appError = new AppError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(appError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handlIllegalArgument(IllegalArgumentException ex) {
        AppError appError = new AppError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(appError);
    }

    private ResponseEntity<Object> buildResponseEntity(AppError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
