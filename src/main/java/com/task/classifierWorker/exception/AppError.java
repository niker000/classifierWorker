package com.task.classifierWorker.exception;

import org.springframework.http.HttpStatus;

public class AppError {
    private HttpStatus status;
    private String message;

    public AppError() {
    }

    public AppError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
