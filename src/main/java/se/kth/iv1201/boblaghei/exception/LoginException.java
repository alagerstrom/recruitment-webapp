package se.kth.iv1201.boblaghei.exception;

/**
 * Exception thrown by the backend if the login failed.
 */
public class LoginException extends Exception{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
