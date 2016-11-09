package com.iotsens.sdk.events;

public enum ActivationState {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    ALL("ALL");

    private String value;

    private ActivationState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
