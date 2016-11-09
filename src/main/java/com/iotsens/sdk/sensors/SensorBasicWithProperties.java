package com.iotsens.sdk.sensors;

import java.util.List;

public class SensorBasicWithProperties extends SensorBasic {

    private List<SensorProperty> properties;

    public List<SensorProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SensorProperty> properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {


        return "SensorBasicWithProperties{" +
                "uniqueId='" + getUniqueId() +
                ", enable=" + isEnable() +
                ", longitude=" + getLongitude() +
                ", latitude=" + getLatitude() +
                ",properties=" + properties +
                "'}'";
    }
}
