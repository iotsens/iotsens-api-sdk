package com.iotsens.sdk.sensors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DefaultGraphType {
    LINE("LINE"),
    BARS("BARS");

    private String value;

    DefaultGraphType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
