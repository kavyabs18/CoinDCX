package com.coindcx.util;

import java.util.logging.Logger;

public class LoggingUtil {
    private static final Logger logger = Logger.getLogger(LoggingUtil.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Exception e) {
        logger.severe(message + ": " + e.getMessage());
    }
}
