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

    public void log(String logMsg) {
        logger.error(logMsg);
    }

    public void log(String logMsg, Exception e) {
        logger.error(logMsg, e);
    }
}
