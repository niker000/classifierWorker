package com.task.classifierWorker.exception;

public class ElementExistException extends RuntimeException {
    public ElementExistException() {
    }

    public ElementExistException(String message) {
        super(message);
    }
}
