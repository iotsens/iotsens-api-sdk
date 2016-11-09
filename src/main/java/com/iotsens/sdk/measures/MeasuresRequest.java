package com.iotsens.sdk.measures;

public class MeasuresRequest {

    public String  sensorId;
    private String variableName;
    private String from;
    private String until;
    private Integer limit;
    private String  dayParam;
    private Integer daysBeforeMargin;
    private Integer daysAfterMargin;

    public MeasuresRequest(String sensorId, String variableName) {
        this.sensorId = sensorId;
        this.variableName = variableName;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getDayParam() {
        return dayParam;
    }

    public void setDayParam(String dayParam) {
        this.dayParam = dayParam;
    }

    public Integer getDaysBeforeMargin() {
        return daysBeforeMargin;
    }

    public void setDaysBeforeMargin(Integer daysBeforeMargin) {
        this.daysBeforeMargin = daysBeforeMargin;
    }

    public Integer getDaysAfterMargin() {
        return daysAfterMargin;
    }

    public void setDaysAfterMargin(Integer daysAfterMargin) {
        this.daysAfterMargin = daysAfterMargin;
    }
}
