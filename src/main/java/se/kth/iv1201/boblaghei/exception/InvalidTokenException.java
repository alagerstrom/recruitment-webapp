package se.kth.iv1201.boblaghei.exception;


public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
    }

    public InvalidTokenException(String s) {
        super(s);
    }
}
