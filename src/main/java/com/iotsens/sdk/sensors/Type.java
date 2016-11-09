package com.iotsens.sdk.sensors;

public enum Type {
    STRING("STRING"),
    UNSIGNED_INTEGER("UNSIGNED_INTEGER"),
    INTEGER("INTEGER"),
    LONG("LONG"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    BOOLEAN("BOOLEAN"),
    LOG("LOG");

    private String value;

    Type(String value) {
        this.value = value;
    }
}
