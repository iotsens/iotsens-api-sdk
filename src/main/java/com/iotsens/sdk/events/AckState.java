package com.iotsens.sdk.events;

public enum AckState {

    NOT_REQUIRED("NOT_REQUIRED"),
    PENDING_ACK("PENDING_ACK"),
    ACKNOWLEDGED("ACKNOWLEDGED");

    private String value;

    private AckState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
