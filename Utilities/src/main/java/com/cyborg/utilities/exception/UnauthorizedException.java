package com.cyborg.utilities.exception;

/**
 * @author saranshk04
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
