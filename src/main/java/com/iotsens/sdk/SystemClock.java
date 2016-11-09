package com.iotsens.sdk;

public class SystemClock implements Clock {
    
    public Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}
