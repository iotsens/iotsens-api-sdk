package com.iotsens.sdk.examples;

import com.iotsens.sdk.IoTSensApiClient;
import com.iotsens.sdk.events.Event;
import com.iotsens.sdk.events.EventsRequest;
import com.iotsens.sdk.events.EventsRequestBuilder;

import java.util.List;

import static com.iotsens.sdk.IoTSensApiClientBuilder.aIoTSensClient;

public class EventsRetrieverExample {

    public static final String APPLICATION_ID = "YOUR_APPLICATION_ID";
    public static final String SECRET = "YOUR_APP_SECRET";
    public static final String DEFAULT_USER = "A_DEFAULT_USER";

    public static void main(String[] args) throws Exception {
        IoTSensApiClient apiClient = aIoTSensClient()
                .withApplication(APPLICATION_ID)
                .withSecret(SECRET)
                .withDefaultUser(DEFAULT_USER)
                .build();

        EventsRequest eventsRequest = EventsRequestBuilder.aEventRequest()
                .addSensorId("YOUR_SENSOR1")
                .addSensorId("YOUR_SENSOR2")
                .withVariableName("A_VARIABLE_NAME")
                .withFrom("20160518")
                .build();

        List<Event> events = apiClient.getEvents(eventsRequest);

        for (Event event : events) {
            System.out.println("event = " + event);
        }

        Integer count = apiClient.countEvents(eventsRequest);

        System.out.println("count = " + count);

    }
}