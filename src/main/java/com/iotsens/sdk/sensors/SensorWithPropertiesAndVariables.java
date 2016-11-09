package com.iotsens.sdk.sensors;

import java.util.List;

public class SensorWithPropertiesAndVariables extends SensorBasicWithProperties {

    private List<SensorVariable> variables;

    public List<SensorVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<SensorVariable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "SensorBasicWithProperties{" +
                "uniqueId='" + getUniqueId() +
                ", enable=" + isEnable() +
                ", longitude=" + getLongitude() +
                ", latitude=" + getLatitude() +
                ",properties=" + getProperties() +
                ",variables=" + getVariables() +
                "'}'";
    }
}
