package com.iotsens.sdk.sensors;

public class SensorProperty {

    private String name;
    private String lastValue;
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }


    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SensorProperty{" +
                "name='" + name + '\'' +
                ", lastValue='" + lastValue + '\'' +
                ", type=" + type +
                '}';
    }
}