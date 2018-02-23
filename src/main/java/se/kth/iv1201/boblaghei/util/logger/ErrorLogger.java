package se.kth.iv1201.boblaghei.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logger used for logging error-messages.
 */
@Component
public class ErrorLogger {

    private final Logger logger = LoggerFactory.getLogger("errorLogger");

    /**
     * Used to log warning message
     *
     * @param logMsg The message to be logged
     * @param e The exception to log
     */
    public void logWarn(String logMsg, Exception e) {
        logger.warn(logMsg, e);
    }

    /**
     * Used to log error messages
     * @param logMsg The message to log
     * @param e The exception to log
     */
    public void logError(String logMsg, Exception e) {
        logger.error(logMsg, e);
    }
}
