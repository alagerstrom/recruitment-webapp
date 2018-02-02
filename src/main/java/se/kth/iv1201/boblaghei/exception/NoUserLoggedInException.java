package se.kth.iv1201.boblaghei.exception;

public class NoUserLoggedInException extends Exception {

    public NoUserLoggedInException() {
    }

    public NoUserLoggedInException(String message) {
        super(message);
    }
}
