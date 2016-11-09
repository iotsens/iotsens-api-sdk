package com.iotsens.sdk.measures.rangemeasures;

public enum SummaryTimeUnit {
    SECONDS("SECONDS"),
    MINUTES("MINUTES"),
    HOURS("HOURS"),
    DAYS("DAYS"),
    WEEKS("WEEKS"),
    MONTHS("MONTHS"),
    YEARS("YEARS");

    private String value;

    SummaryTimeUnit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}