package com.iotsens.sdk.examples;

import com.iotsens.sdk.IoTSensApiClient;
import com.iotsens.sdk.measures.*;
import com.iotsens.sdk.measures.rangemeasures.*;

import java.util.List;

import static com.iotsens.sdk.IoTSensApiClientBuilder.aIoTSensClient;
import static com.iotsens.sdk.measures.MultiSensorMeasuresRequestBuilder.aMultiSensorMeasureRequest;

public class MeasuresRetrieverExample {

    public static final String APPLICATION_ID = "YOUR_APPLICATION_ID";
    public static final String SECRET = "YOUR_APP_SECRET";
    public static final String DEFAULT_USER = "A_DEFAULT_USER";

    public static void main(String[] args) throws Exception {

        IoTSensApiClient apiClient = aIoTSensClient()
                .withApplication(APPLICATION_ID)
                .withSecret(SECRET)
                .withDefaultUser(DEFAULT_USER)
                .build();

        MeasuresRequest request = MeasuresRequestBuilder.aMeasureRequest("YOUR_SENSOR1", "TEMP")
                .build();
        List<Measure> measures = apiClient.getMeasures(request);
        System.out.println("measures  = " + measures);

        MultiSensorMeasuresRequest multiSensorRequest = aMultiSensorMeasureRequest("YOUR_SENSOR1", "TEMP", 40)
                .addSensorId("YOUR_SENSOR2")
                .withFrom("20160523")
                .withUntil("20160524")
                .build();

        List<Measure> multiSensorMeasures = apiClient.getMeasures(multiSensorRequest);
        System.out.println("multiSensorMeasures = " + multiSensorMeasures);

        RangeMeasuresRequest rangeMeasuresRequest = RangeMeasuresRequestBuilder.aRangeMeasureRequest("YOUR_SENSOR1", "A_VARIABLE_NAME", SummaryTimeUnit.HOURS, SummaryOperationType.COUNT)
                .withFrom("20160523")
                .withUntil("20160524")
                .withUnit(3)
                .build();
        List<RangeMeasure> rangeMeasures = apiClient.getRangeMeasures(rangeMeasuresRequest);

        for (RangeMeasure rangeMeasure : rangeMeasures) {
            System.out.println("rangeMeasure = " + rangeMeasure);
        }

        RangeMeasuresRequest multiSensorRangeMeasuresRequest = RangeMeasuresRequestBuilder.aRangeMeasureRequest("YOUR_SENSOR1", "A_VARIABLE_NAME", SummaryTimeUnit.HOURS, SummaryOperationType.COUNT)
                .addSensorId("YOUR_SENSOR2")
                .withFrom("20160523")
                .withUntil("20160524")
                .withUnit(3)
                .build();
        List<RangeMeasure> multiSensorRangeMeasures = apiClient.getRangeMeasures(rangeMeasuresRequest);

        for (RangeMeasure rangeMeasure : multiSensorRangeMeasures) {
            System.out.println("rangeMeasure = " + rangeMeasure);
        }

    }
}
