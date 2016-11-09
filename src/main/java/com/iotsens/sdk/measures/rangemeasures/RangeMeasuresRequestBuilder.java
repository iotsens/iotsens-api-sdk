package com.iotsens.sdk.measures.rangemeasures;

public class RangeMeasuresRequestBuilder {

    private RangeMeasuresRequest request;

    public static RangeMeasuresRequestBuilder aRangeMeasureRequest(String sensorId, String variableName, SummaryTimeUnit rangeUnit, SummaryOperationType summaryOperation) {
        return new RangeMeasuresRequestBuilder(sensorId, variableName, rangeUnit, summaryOperation);
    }

    private RangeMeasuresRequestBuilder (String sensorId, String variableName, SummaryTimeUnit rangeUnit, SummaryOperationType summaryOperation) {
        request = new RangeMeasuresRequest(sensorId, variableName, rangeUnit, summaryOperation);
    }

    public RangeMeasuresRequestBuilder addSensorId(String sensorId) {
        request.addSensorId(sensorId);
        return this;
    }

    public RangeMeasuresRequestBuilder withFrom(String from) {
        request.setFrom(from);
        return this;
    }

    public RangeMeasuresRequestBuilder withUntil(String until) {
        request.setUntil(until);
        return this;
    }

    public RangeMeasuresRequestBuilder withUnit(Integer unit) {
        request.setUnit(unit);
        return this;
    }

    public RangeMeasuresRequest build() {
        return request;
    }
}
