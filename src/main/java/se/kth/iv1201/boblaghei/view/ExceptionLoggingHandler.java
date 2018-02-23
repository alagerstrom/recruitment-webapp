package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.kth.iv1201.boblaghei.exception.*;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;
import se.kth.iv1201.boblaghei.util.logger.SecurityLogger;

/**
 * Used to catch exceptions and log them.
 */
@ControllerAdvice
public class ExceptionLoggingHandler {

    @Autowired
    ErrorLogger errorLogger;

    @Autowired
    SecurityLogger securityLogger;

    /**
     * Catch DuplicateUsernameException and log it
     *
     * @param e The exception to catch
     * @throws DuplicateUsernameException the same exception
     */
    @ExceptionHandler(DuplicateUsernameException.class)
    public void logDuplicateUsernameException(DuplicateUsernameException e) throws DuplicateUsernameException {
        errorLogger.logWarn(e.getMessage(), e);
        throw e;
    }

    /**
     * Catch NoUserLoggedInException and log it
     *
     * @param e The exception to catch
     * @throws NoUserLoggedInException the same exception
     */
    @ExceptionHandler(NoUserLoggedInException.class)
    public void logNoUserLoggedInException(NoUserLoggedInException e) throws NoUserLoggedInException {
        errorLogger.logError(e.getMessage(), e);
        throw e;
    }

    /**
     * Catch ResourceNotFoundException and log it
     *
     * @param e The exception to catch
     * @throws ResourceNotFoundException the same exception
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public void logResourceNotFoundException(ResourceNotFoundException e) throws ResourceNotFoundException {
        errorLogger.logError(e.getMessage(), e);
        throw e;
    }

    /**
     * Catch LoginException and log it
     *
     * @param e The exception to catch
     * @throws LoginException the same exception
     */
    @ExceptionHandler(LoginException.class)
    public void logLoginException(LoginException e) throws LoginException {
        securityLogger.logWarn(e.getMessage(), e);
        throw e;
    }

    /**
     * Catch InvalidTokenException and log it
     *
     * @param e The exception to catch
     * @throws InvalidTokenException the same exception
     */
    @ExceptionHandler(InvalidTokenException.class)
    public void logInvalidTokenException(InvalidTokenException e) throws InvalidTokenException {
        securityLogger.logFatal(e.getMessage(), e);
        throw e;
    }

    /**
     * Catch any unhandled exception and log it
     *
     * @param e The exception to log
     */
    @ExceptionHandler(Exception.class)
    public void logErrors(Exception e) {
        errorLogger.logError(e.getMessage(), e);
    }
}
