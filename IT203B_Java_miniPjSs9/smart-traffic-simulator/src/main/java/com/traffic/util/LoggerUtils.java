package com.traffic.util;

import java.time.LocalTime;

public class LoggerUtils {

    public static void log(String msg) {
        System.out.println("[Time " + LocalTime.now() + "] " + msg);
    }
}