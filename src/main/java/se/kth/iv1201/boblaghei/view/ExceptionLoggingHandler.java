package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.kth.iv1201.boblaghei.exception.*;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;
import se.kth.iv1201.boblaghei.util.logger.SecurityLogger;

@ControllerAdvice
public class ExceptionLoggingHandler {

    @Autowired
    ErrorLogger errorLogger;

    @Autowired
    SecurityLogger securityLogger;


    @ExceptionHandler(DuplicateUsernameException.class)
    public void logDuplicateUsernameException(DuplicateUsernameException e) throws DuplicateUsernameException {
        errorLogger.logWarn(e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(NoUserLoggedInException.class)
    public void logNoUserLoggedInException(NoUserLoggedInException e) throws NoUserLoggedInException {
        errorLogger.logError(e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public void logResourceNotFoundException(ResourceNotFoundException e) throws ResourceNotFoundException {
        errorLogger.logError(e.getMessage(), e);
        throw e;
    }
    

    @ExceptionHandler(LoginException.class)
    public void logLoginException(LoginException e) throws LoginException {
        securityLogger.logWarn(e.getMessage(), e);
        throw e;
    }

    @ExceptionHandler(InvalidTokenException.class)
    public void logInvalidTokenException(InvalidTokenException e) throws InvalidTokenException {
        securityLogger.logFatal(e.getMessage(), e);
        throw e;

    }

    @ExceptionHandler(Exception.class)
    public void logErrors(Exception e) {
        errorLogger.logError(e.getMessage(), e);
    }
}
