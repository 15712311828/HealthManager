package com.healthmanager.exception;

public class BusinessException extends RuntimeException {

    static final long serialVersionUID = -4643524325345l;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
