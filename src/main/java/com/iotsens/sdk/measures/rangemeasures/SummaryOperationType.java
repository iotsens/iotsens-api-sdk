package com.iotsens.sdk.measures.rangemeasures;

public enum SummaryOperationType {

    MAX("MAX"),
    MIN("MIN"),
    SUM("SUM"),
    COUNT("COUNT"),
    AVERAGE("AVERAGE"),
    LAST("LAST"),
    DIFFERENCE_PREVIOUS("DIFFERENCE_PREVIOUS");

    String value;

    SummaryOperationType (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
