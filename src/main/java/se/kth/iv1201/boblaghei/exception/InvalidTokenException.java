package se.kth.iv1201.boblaghei.exception;

/**
 * Exception thrown by the backend if the sent JWT token is invalid.
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
    }

    public InvalidTokenException(String s) {
        super(s);
    }
}
