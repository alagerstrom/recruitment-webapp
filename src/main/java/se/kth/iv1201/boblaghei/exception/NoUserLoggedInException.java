package se.kth.iv1201.boblaghei.exception;

/**
 * Exception thrown when a service needing a logged in user try to run without a logged in user.
 */
public class NoUserLoggedInException extends Exception {

    public NoUserLoggedInException() {
    }

    public NoUserLoggedInException(String message) {
        super(message);
    }
}
