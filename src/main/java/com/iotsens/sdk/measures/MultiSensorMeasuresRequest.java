package com.iotsens.sdk.measures;

import java.util.ArrayList;
import java.util.List;

public class MultiSensorMeasuresRequest {

    List<String> sensorIds;
    String variableName;
    String from;
    String until;
    Integer limit;

    public MultiSensorMeasuresRequest(String sensorId, String variableName, Integer limit) {
        this.sensorIds = new ArrayList<String>();
        sensorIds.add(sensorId);
        this.variableName = variableName;
        this.limit = limit;
    }

    public List<String> getSensorIds() {
        return sensorIds;
    }

    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public void addSensorId(String sensorId) {
        sensorIds.add(sensorId);
    }

}