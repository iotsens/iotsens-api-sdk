package com.iotsens.sdk.measures.rangemeasures;

import com.iotsens.sdk.measures.Measure;

public class RangeMeasure extends Measure {

    private SummaryTimeUnit summaryTimeUnit;
    private String rawValue;

    public SummaryTimeUnit getSummaryTimeUnit() {
        return summaryTimeUnit;
    }

    public void setSummaryTimeUnit(SummaryTimeUnit summaryTimeUnit) {
        this.summaryTimeUnit = summaryTimeUnit;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public String toString() {
        return "RangeMeasure{" +
                "summaryTimeUnit=" + summaryTimeUnit +
                "} " + super.toString();
    }
}
