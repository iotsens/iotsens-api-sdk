package com.iotsens.sdk.examples;

import com.iotsens.sdk.IoTSensApiClient;
import com.iotsens.sdk.sensors.*;

import java.util.ArrayList;
import java.util.List;

import static com.iotsens.sdk.IoTSensApiClientBuilder.aIoTSensClient;
import static com.iotsens.sdk.sensors.SensorsRequestBuilder.aSensorRequest;

public class SensorsRetrieverExample {

    public static final String APPLICATION_ID = "YOUR_APPLICATION_ID";
    public static final String SECRET = "YOUR_APP_SECRET";
    public static final String DEFAULT_USER = "A_DEFAULT_USER";

    public static void main(String[] args) throws Exception {

        IoTSensApiClient apiClient = aIoTSensClient()
                .withApplication(APPLICATION_ID)
                .withSecret(SECRET)
                .withDefaultUser(DEFAULT_USER)
                .build();

        SensorsRequest sensorsRequest = aSensorRequest()
                .withNorth(39.978801)
                .withWest(-0.039514)
                .withSouth(39.976915)
                .withEast(-0.038205)
                .build();

        for (SensorBasic sensorBasic :  apiClient.getSensors(sensorsRequest)) {
            System.out.println("sensor = " + sensorBasic);
        }

        List<String> listOfSensorIds = new ArrayList<String>();
        listOfSensorIds.add("YOUR_SENSOR1");
        listOfSensorIds.add("YOUR_SENSOR2");

        for (SensorBasicWithProperties sensorBasicWithProperties : apiClient.getSensors(listOfSensorIds)) {
            System.out.println("sensor basic with properties = " + sensorBasicWithProperties);
            System.out.println("sensor with properties and variables = " + apiClient.getSensor(sensorBasicWithProperties.getUniqueId()));
        }



    }
}
