package com.example.demo.logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookStateChangeLogger {
private static int count = 0;
@After("""
        execution(*com.example.demo.controller.BookController.*(..))""")
    public void countAccessSearchController(){
    count++;
    System.out.println("Number of times the BookController accessed: " + count);
}
}


