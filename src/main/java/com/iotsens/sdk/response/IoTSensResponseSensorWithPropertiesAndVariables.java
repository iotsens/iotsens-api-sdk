package com.iotsens.sdk.response;

import com.iotsens.sdk.sensors.SensorWithPropertiesAndVariables;

public class IoTSensResponseSensorWithPropertiesAndVariables {
    private boolean success;
    private SensorWithPropertiesAndVariables data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SensorWithPropertiesAndVariables getData() {
        return data;
    }

    public void setData(SensorWithPropertiesAndVariables data) {
        this.data = data;
    }
}
