package se.kth.iv1201.boblaghei.util.exception;

/**
 * Exception thrown when a user is registered with an already existing username.
 */
public class DuplicateUsernameException extends Exception {

    public DuplicateUsernameException(String message) {
        super(message);
    }
}
