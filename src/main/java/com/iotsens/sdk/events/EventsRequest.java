package com.iotsens.sdk.events;

import java.util.ArrayList;
import java.util.List;

public class EventsRequest {

    private List<String> sensorIds;
    private String variableName;
    private String from;
    private String until;
    private String name;
    private ActivationState activation;
    private AckState ackState;
    private EventType eventType;
    private Integer offset;
    private Integer limit;

    public EventsRequest() {
        sensorIds = new ArrayList<String>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivationState getActivation() {
        return activation;
    }

    public void setActivation(ActivationState activation) {
        this.activation = activation;
    }

    public AckState getAckState() {
        return ackState;
    }

    public void setAckState(AckState ackState) {
        this.ackState = ackState;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void addSensorId(String sensorId) {
        sensorIds.add(sensorId);
    }
}
