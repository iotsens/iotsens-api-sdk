package com.iotsens.sdk.measures.rangemeasures;

import java.util.ArrayList;
import java.util.List;

public class RangeMeasuresRequest {

    private List<String> sensorIds;
    private String variableName;
    private String from;
    private String until;
    private SummaryTimeUnit rangeUnit;
    private Integer unit;
    private SummaryOperationType summaryOperation;

    public RangeMeasuresRequest(String sensorId, String variableName, SummaryTimeUnit rangeUnit, SummaryOperationType summaryOperation) {
        this.sensorIds = new ArrayList<String>();
        this.sensorIds.add(sensorId);
        this.variableName = variableName;
        this.rangeUnit = rangeUnit;
        this.summaryOperation = summaryOperation;
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

    public SummaryTimeUnit getRangeUnit() {
        return rangeUnit;
    }

    public void setRangeUnit(SummaryTimeUnit rangeUnit) {
        this.rangeUnit = rangeUnit;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public SummaryOperationType getSummaryOperation() {
        return summaryOperation;
    }

    public void setSummaryOperation(SummaryOperationType summaryOperation) {
        this.summaryOperation = summaryOperation;
    }

    public void addSensorId(String sensorId) {
        sensorIds.add(sensorId);
    }

    public boolean hasSingleSensorId() {
        return sensorIds.size() == 1;
    }

}
