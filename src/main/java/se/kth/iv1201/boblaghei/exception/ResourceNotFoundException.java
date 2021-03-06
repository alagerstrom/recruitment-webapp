package se.kth.iv1201.boblaghei.exception;

/**
 * Exception thrown when a rest is searched for but not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
