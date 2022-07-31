package com.cyborg.utilities.exception;

/**
 * @author saranshk04
 */
public class ResourceNotExistException extends RuntimeException {

    public ResourceNotExistException() {
        super();
    }

    public ResourceNotExistException(String message) {
        super(message);
    }
}
