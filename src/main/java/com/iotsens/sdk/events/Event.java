package com.iotsens.sdk.events;

public class Event {

    private String timestamp;
    private String deactivationTime;
    private String variableName;
    private String name;
    private String sensorId;
    private String data;
    private boolean active;
    private EventType type;
    private AckState ackState;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeactivationTime() {
        return deactivationTime;
    }

    public void setDeactivationTime(String deactivationTime) {
        this.deactivationTime = deactivationTime;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public AckState getAckState() {
        return ackState;
    }

    public void setAckState(AckState ackState) {
        this.ackState = ackState;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp='" + timestamp + '\'' +
                ", deactivationTime='" + deactivationTime + '\'' +
                ", variableName='" + variableName + '\'' +
                ", name='" + name + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", data='" + data + '\'' +
                ", active=" + active +
                ", type=" + type +
                ", ackState=" + ackState +
                '}';
    }
}
