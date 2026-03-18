package com.traffic.exception;

public class TrafficJamException extends RuntimeException {
    public TrafficJamException(String msg) {
        super(msg);
    }
}