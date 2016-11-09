package com.iotsens.sdk.measures;

public class MeasuresRequestBuilder {

    private MeasuresRequest measuresRequest;

    public static MeasuresRequestBuilder aMeasureRequest(String sensorId, String variableName) {
        return new MeasuresRequestBuilder(sensorId, variableName);
    }

    private MeasuresRequestBuilder(String sensorId, String variableName) {
        measuresRequest = new MeasuresRequest(sensorId, variableName);
    }

    public MeasuresRequestBuilder withFrom(String from) {
        measuresRequest.setFrom(from);
        return this;
    }

    public MeasuresRequestBuilder withUntil(String until) {
        measuresRequest.setUntil(until);
        return this;
    }

    public MeasuresRequestBuilder withLimit(Integer limit) {
        measuresRequest.setLimit(limit);
        return this;
    }

    public MeasuresRequestBuilder withDayParam(String dayParam) {
        measuresRequest.setDayParam(dayParam);
        return this;
    }

    public MeasuresRequestBuilder withDaysBeforeMargin(Integer daysBeforeMargin) {
        measuresRequest.setDaysBeforeMargin(daysBeforeMargin);
        return this;
    }

    public MeasuresRequestBuilder withDaysAfterMargin(Integer daysAfterMargin) {
        measuresRequest.setDaysAfterMargin(daysAfterMargin);
        return this;
    }

    public MeasuresRequest build() {
        return measuresRequest;
    }
}