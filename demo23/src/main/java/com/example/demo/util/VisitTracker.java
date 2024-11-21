package com.example.demo.util;

public class VisitTracker {
    private static int visitCount = 0;

    public static void incrementVisit() {
        visitCount++;
    }

    public static int getVisitCount() {
        return visitCount;
    }
}
