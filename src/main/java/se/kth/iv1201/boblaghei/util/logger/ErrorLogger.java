package se.kth.iv1201.boblaghei.util.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogger {

    private final Logger logger = LoggerFactory.getLogger("errorLogger");

    public void log(String logMsg) {
        logger.info(logMsg);
    }
}
