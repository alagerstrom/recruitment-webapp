package se.kth.iv1201.boblaghei.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

/**
 * Logger used for logging security-related messages
 */
@Component
public class SecurityLogger {

    private final Logger logger = LoggerFactory.getLogger("securityLogger");
    private final Marker marker = MarkerFactory.getMarker("FATAL");

    /**
     * Used to log warning
     *
     * @param logMsg The message to log
     * @param e The exception to log
     */
    public void logWarn(String logMsg, Exception e) {
        logger.warn(logMsg, e);
    }

    /**
     * Used to log errors
     *
     * @param logMsg The message to log
     * @param e The exception to log
     */
    public void logError(String logMsg, Exception e) {
        logger.error(logMsg, e);
    }

    /**
     * Used to log fatal errors
     *
     * @param logMsg The message to log
     * @param e The exception to log
     */
    public void logFatal(String logMsg, Exception e) {
        logger.error(marker + " " + logMsg, e);
    }
}
