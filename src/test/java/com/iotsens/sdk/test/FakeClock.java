package com.iotsens.sdk.test;

import com.iotsens.sdk.Clock;

public class FakeClock implements Clock {
    private Long timestamp;

    public FakeClock(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCurrentTimestamp() {
        return timestamp;
    }
}
