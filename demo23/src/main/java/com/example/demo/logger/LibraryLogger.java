package com.example.demo.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LibraryLogger {

    private static final AtomicInteger accessCount = new AtomicInteger(0);
    private static final AtomicInteger bookStateChangeCount = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(LibraryLogger.class);

    @After("execution(* com.example.demo.controller.BookController.addBook(..)) || execution(* com.example.demo.controller.BookController.borrowBook(..)) || execution(* com.example.demo.controller.BookController.returnBook(..))")
    public void logBookStateChange(JoinPoint joinPoint) {
        int currentChangeCount = bookStateChangeCount.incrementAndGet();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Book state change count: {} | Method: {} | Arguments: {}", currentChangeCount, methodName, methodArgs);
    }

    @Before("execution(* com.example.demo.controller.BookController.*(..))")
    public void logLibraryAccess(JoinPoint joinPoint) {
        int currentAccessCount = accessCount.incrementAndGet();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Total library access count: {} | Method: {} | Arguments: {}", currentAccessCount, methodName, methodArgs);
    }
}
