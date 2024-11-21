package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    // Hàm ghi log
    public static void log(String message) {
        logger.info(message);
    }
}
