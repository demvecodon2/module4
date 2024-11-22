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

    // Biến đếm số lượng truy cập
    private static final AtomicInteger accessCount = new AtomicInteger(0);

    // Biến đếm số lượng các hành động thay đổi trạng thái sách
    private static final AtomicInteger bookStateChangeCount = new AtomicInteger(0);

    private static final Logger logger = LoggerFactory.getLogger(LibraryLogger.class);

    // Join point để ghi log khi có hành động thay đổi trạng thái sách
    @After("execution(* com.example.demo.controller.BookController.*(..)) && (execution(* create*(..)) || execution(* update*(..)) || execution(* delete*(..)))")
    public void logBookStateChange(JoinPoint joinPoint) {
        int currentChangeCount = bookStateChangeCount.incrementAndGet();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        // Ghi log thông tin về hành động thay đổi trạng thái sách
        logger.info("Book state change count: {} | Method: {} | Arguments: {}", currentChangeCount, methodName, methodArgs);
    }

    // Join point để ghi log số lượng truy cập vào thư viện
    @Before("execution(* com.example.demo.controller.BookController.*(..))")
    public void logLibraryAccess(JoinPoint joinPoint) {
        int currentAccessCount = accessCount.incrementAndGet();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("Total library access count: {} | Method: {} | Arguments: {}", currentAccessCount, methodName, methodArgs);
    }
}
