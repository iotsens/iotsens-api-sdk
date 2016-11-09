package com.iotsens.sdk.events;

public enum EventType {

    ALARM("ALARM"),
    WARNING("WARNING"),
    INFO("INFO");

    private String value;

    private EventType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
