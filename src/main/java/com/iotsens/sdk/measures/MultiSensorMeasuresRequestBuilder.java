package com.iotsens.sdk.measures;

import java.util.List;

public class MultiSensorMeasuresRequestBuilder {
    private List<String> sensorIds;
    private String variableName;
    private String from;
    private String until;
    private Integer limit;
    private MultiSensorMeasuresRequest multiSensorMeasuresRequest;


    public static MultiSensorMeasuresRequestBuilder aMultiSensorMeasureRequest(String sensorId, String variableName, Integer limit) {
        return new MultiSensorMeasuresRequestBuilder(sensorId, variableName, limit);
    }

    private MultiSensorMeasuresRequestBuilder(String sensorId, String variableName, Integer limit) {
        multiSensorMeasuresRequest = new MultiSensorMeasuresRequest(sensorId, variableName, limit);
    }

    public MultiSensorMeasuresRequestBuilder addSensorId(String sensorId) {
        multiSensorMeasuresRequest.addSensorId(sensorId);
        return this;
    }

    public MultiSensorMeasuresRequestBuilder withFrom(String from) {
        multiSensorMeasuresRequest.setFrom(from);
        return this;
    }

    public MultiSensorMeasuresRequestBuilder withUntil(String until) {
        multiSensorMeasuresRequest.setUntil(until);
        return this;
    }

    public MultiSensorMeasuresRequest build() {
        return multiSensorMeasuresRequest;
    }
}