package se.kth.iv1201.boblaghei.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Logger used for logging security-related messages
 */
@Component
public class SecurityLogger {

    private final Logger logger = LoggerFactory.getLogger("securityLogger");

    public void log(String logMsg) {
        logger.info(logMsg);
    }
}
