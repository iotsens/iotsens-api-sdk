package com.iotsens.sdk.sensors;

public class SensorVariable {

    private String name;
    private String description;
    private Type type;
    private DefaultGraphType defaultGraphType;
    private Integer maxInactivitySeconds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DefaultGraphType getDefaultGraphType() {
        return defaultGraphType;
    }

    public void setDefaultGraphType(DefaultGraphType defaultGraphType) {
        this.defaultGraphType = defaultGraphType;
    }

    public Integer getMaxInactivitySeconds() {
        return maxInactivitySeconds;
    }

    public void setMaxInactivitySeconds(Integer maxInactivitySeconds) {
        this.maxInactivitySeconds = maxInactivitySeconds;
    }

    @Override
    public String toString() {
        return "SensorVariable{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", defaultGraphType=" + defaultGraphType +
                ", maxInactivitySeconds=" + maxInactivitySeconds +
                '}';
    }
}