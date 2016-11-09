package com.iotsens.sdk;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotsens.sdk.events.*;
import com.iotsens.sdk.exceptions.AuthenticationException;
import com.iotsens.sdk.exceptions.UserCanNotAccessException;
import com.iotsens.sdk.jackson.CustomJacksonMapper;
import com.iotsens.sdk.measures.*;
import com.iotsens.sdk.measures.rangemeasures.*;
import com.iotsens.sdk.response.ExceptionMapper;
import com.iotsens.sdk.sensors.*;
import com.iotsens.sdk.test.FakeClock;
import com.iotsens.sdk.test.TestUtils;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.iotsens.sdk.measures.MeasuresRequestBuilder.aMeasureRequest;
import static com.iotsens.sdk.measures.MultiSensorMeasuresRequestBuilder.aMultiSensorMeasureRequest;
import static com.iotsens.sdk.measures.rangemeasures.RangeMeasuresRequestBuilder.aRangeMeasureRequest;
import static com.iotsens.sdk.measures.rangemeasures.SummaryTimeUnit.DAYS;
import static com.iotsens.sdk.measures.rangemeasures.SummaryTimeUnit.MINUTES;
import static com.iotsens.sdk.sensors.Type.*;
import static com.iotsens.sdk.sensors.DefaultGraphType.*;
import static com.iotsens.sdk.sensors.SensorsRequestBuilder.aSensorRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class IoTSensApiClientTest {

    public static final int UNAUTHORIZED_HTTP_STATUS = 401;
    private static String A_USER = "USER";
    private static String AN_APP = "APP";
    private static long A_TIMESTAMP = 123456789L;

    private MockWebServer mockWebServer;
    private IoTSensApiClient apiClient;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();

        Credentials credentials = new Credentials(AN_APP, "SECRET", A_USER);
        Clock fakeClock = new FakeClock(A_TIMESTAMP);
        String baseUrl = mockWebServer.url("/v1/").url().toURI().toString();

        ResponseMapper responseMapper = new ResponseMapper();

        apiClient = new IoTSensApiClient(fakeClock, credentials, baseUrl, responseMapper);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void sensorsCall() throws Exception {

        enqueueResponse("/sensors.json");

        List<SensorBasic> sensors = apiClient.getSensors();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        String application = recordedRequest.getHeader("GG-Requester-Application");
        String timestamp = recordedRequest.getHeader("GG-Request-Timestamp");
        String user = recordedRequest.getHeader("IOT-Authorized-User");
        String signature = recordedRequest.getHeader("GG-Request-Signature");

        assertThat(application, equalTo(AN_APP));
        assertThat(Long.parseLong(timestamp), equalTo(A_TIMESTAMP));
        assertThat(user, equalTo(A_USER));
        assertThat(signature, equalTo("3dc2882b10ae8388a1569b9599c1ea47"));

        assertThat(sensors.size(), equalTo(3));

        SensorBasic sensor = sensors.get(0);
        assertTrue(sensor.isEnable());
        assertThat(sensor.getLatitude(), equalTo(-0.5));
        assertThat(sensor.getLongitude(), equalTo(30.5));
        assertThat(sensor.getUniqueId(), equalTo("003"));
        assertThat(sensor.getCategory().getName(), equalTo("Oficinas FACSA"));
        assertThat(sensor.getCategory().getId(), equalTo(3));
        assertThat(sensor.getCategory().getListingOrder(), equalTo(70));
        assertThat(sensor.getSourceTemplate().getId(), equalTo(14));
        assertThat(sensor.getSourceTemplate().getTemplateName(), equalTo("Oficina FACSA"));
    }

    @Test
    public void sensorsWithParams() throws Exception {
        enqueueResponse("/sensors.json");

        SensorsRequest sensorRequest = aSensorRequest()
                .withEast(30.0)
                .withNorth(41.2)
                .withSouth(-0.4)
                .withWest(-34.522)
                .withLimit(10)
                .withOffset(5)
                .withQuery("query")
                .withCategoryId(6)
                .withTemplateId(14)
                .build();
        apiClient.getSensors(sensorRequest);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl url = getHttpUrl(recordedRequest);

        assertThat(url.queryParameter("east"), equalTo(sensorRequest.getEast().toString()));
        assertThat(url.queryParameter("north"), equalTo(sensorRequest.getNorth().toString()));
        assertThat(url.queryParameter("south"), equalTo(sensorRequest.getSouth().toString()));
        assertThat(url.queryParameter("west"), equalTo(sensorRequest.getWest().toString()));
        assertThat(url.queryParameter("limit"), equalTo(sensorRequest.getLimit().toString()));
        assertThat(url.queryParameter("offset"), equalTo(sensorRequest.getOffset().toString()));
        assertThat(url.queryParameter("query"), equalTo(sensorRequest.getQuery().toString()));
        assertThat(url.queryParameter("categoryId"), equalTo(sensorRequest.getCategoryId().toString()));
        assertThat(url.queryParameter("templateId"), equalTo(sensorRequest.getTemplateId().toString()));

    }

    @Test
    public void listOfSensors() throws Exception {
        enqueueResponse("/sensor_info.json");

        List<String> sensorIds = new ArrayList<String>();
        List<SensorBasicWithProperties> sensors = apiClient.getSensors(sensorIds);

        SensorBasicWithProperties sensor = sensors.get(0);
        SensorProperty property = sensor.getProperties().get(0);

        assertThat(property.getLastValue(), equalTo("hola"));
        assertThat(property.getName(), equalTo("DIRECCION"));
        assertThat(property.getType(), equalTo(STRING));

    }

    @Test
    public void sensorWithVariables() throws Exception {

        enqueueResponse("/sensor_detail_info.json");

        SensorWithPropertiesAndVariables sensor = apiClient.getSensor("003");
        SensorVariable partesGeneradosVariable = sensor.getVariables().get(0);

        assertThat(partesGeneradosVariable.getType(),equalTo(INTEGER));
        assertThat(partesGeneradosVariable.getName(),equalTo("PARTES_GENERADOS"));
        assertThat(partesGeneradosVariable.getDefaultGraphType(),equalTo(LINE));
        assertThat(partesGeneradosVariable.getDescription(),equalTo("Partes Generados"));
        assertThat(partesGeneradosVariable.getMaxInactivitySeconds(),equalTo(2));
    }

    @Test
    public void oneVariableMeasures() throws Exception {
        enqueueResponse("/one_var_measures.json");

        MeasuresRequest request = aMeasureRequest("TEMP01", "TEMP")
                .withDayParam("20160520")
                .withDaysAfterMargin(2)
                .withDaysBeforeMargin(4)
                .withFrom("20160501")
                .withUntil("20160620")
                .withLimit(20)
                .build();

        List<Measure> measuresList = apiClient.getMeasures(request);
        Measure measure = measuresList.get(0);
        assertThat(measure.getSensorId(),equalTo("TEMP01"));
        assertThat(measure.getTimestamp(),equalTo("24/05/2016 15:58:06"));
        assertThat(measure.getValue(),equalTo("20.40"));
        assertThat(measure.getVariableName(),equalTo("TEMP"));

    }


    @Test
    public void measuresWithParams() throws Exception {
        enqueueResponse("/one_var_measures.json");

        MeasuresRequest request = aMeasureRequest("TEMP01", "TEMP")
                .withDayParam("20160520")
                .withDaysAfterMargin(2)
                .withDaysBeforeMargin(4)
                .withFrom("20160501")
                .withUntil("20160620")
                .withLimit(20)
                .build();
        List<Measure> measuresList = apiClient.getMeasures(request);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl url = getHttpUrl(recordedRequest);

        assertThat(url.queryParameter("dayParam"), equalTo(request.getDayParam().toString()));
        assertThat(url.queryParameter("daysAfterMargin"), equalTo(request.getDaysAfterMargin().toString()));
        assertThat(url.queryParameter("daysBeforeMargin"), equalTo(request.getDaysBeforeMargin().toString()));
        assertThat(url.queryParameter("from"), equalTo(request.getFrom().toString()));
        assertThat(url.queryParameter("until"), equalTo(request.getUntil().toString()));
        assertThat(url.queryParameter("limit"), equalTo(request.getLimit().toString()));
    }

    @Test
    public void measuresMultiSensor() throws Exception {
        enqueueResponse("/multi_sensor_measures.json");

        MultiSensorMeasuresRequest measuresRequest = aMultiSensorMeasureRequest("TEMP01", "TEMP", 2)
                .addSensorId("TEMP02")
                .withFrom("20160501")
                .withUntil("20160620")
                .build();

        List<Measure> measuresList = apiClient.getMeasures(measuresRequest);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        Measure secondMeasure = measuresList.get(1);
        assertThat(secondMeasure.getSensorId(),equalTo("TEMP02"));
        assertThat(secondMeasure.getTimestamp(),equalTo("25/05/2016 08:24:14"));
        assertThat(secondMeasure.getValue(),equalTo("17.79"));
        assertThat(secondMeasure.getVariableName(),equalTo("TEMP"));
    }

    @Test
    public void oneVariableRangeMeasures() throws Exception {
        enqueueResponse("/one_sensor_rangemeasures.json");

        RangeMeasuresRequest request = aRangeMeasureRequest("TEMP01", "TEMP", DAYS, SummaryOperationType.SUM).build();

        List<RangeMeasure> measuresList = apiClient.getRangeMeasures(request);
        RangeMeasure measure = measuresList.get(0);
        assertThat(measure.getSensorId(),equalTo("TEMP01"));
        assertThat(measure.getTimestamp(),equalTo("01/05/2016 04:00:00"));
        assertThat(measure.getValue(),equalTo("21.03"));
        assertThat(measure.getVariableName(),equalTo("TEMP"));
        assertThat(measure.getSummaryTimeUnit(), equalTo(DAYS));

    }

    @Test
    public void oneVariableRangeMeasuresWithParams() throws Exception {
        enqueueResponse("/one_sensor_rangemeasures.json");

        RangeMeasuresRequest request = aRangeMeasureRequest("TEMP01", "TEMP", DAYS, SummaryOperationType.SUM)
                .withFrom("20160501")
                .withUntil("20160620")
                .withUnit(3)
                .build();
        List<RangeMeasure> measuresList = apiClient.getRangeMeasures(request);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl url = getHttpUrl(recordedRequest);


        assertThat(url.queryParameter("rangeUnit"), equalTo(request.getRangeUnit().toString()));
        assertThat(url.queryParameter("summaryOperation"), equalTo(request.getSummaryOperation().toString()));
        assertThat(url.queryParameter("from"), equalTo(request.getFrom()));
        assertThat(url.queryParameter("until"), equalTo(request.getUntil()));
        assertThat(url.queryParameter("unit"), equalTo(request.getUnit().toString()));
    }


    @Test
    public void rangeMeasuresMultiSensor() throws Exception {
        enqueueResponse("/multi_sensor_rangemeasures.json");

        RangeMeasuresRequest request = aRangeMeasureRequest("TEMP01", "TEMP", MINUTES, SummaryOperationType.SUM)
                .addSensorId("TEMP02")
                .build();

        List<RangeMeasure> measuresList = apiClient.getRangeMeasures(request);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertThat(recordedRequest.getPath(), equalTo("/v1/rangemeasures"));
        assertThat(recordedRequest.getMethod(), equalTo("POST"));

        RangeMeasure measure = measuresList.get(0);
        assertThat(measure.getSensorId(),equalTo("TEMP02"));
        assertThat(measure.getTimestamp(),equalTo("10/05/2016 02:00:00"));
        assertThat(measure.getValue(),equalTo("13.24"));
        assertThat(measure.getRawValue(),equalTo("13.239999771118164"));
        assertThat(measure.getVariableName(),equalTo("TEMP"));
        assertThat(measure.getSummaryTimeUnit(), equalTo(MINUTES));

    }

    @Test
    public void eventsMultiSensor() throws Exception {
        enqueueResponse("/multi_sensor_events.json");

        EventsRequest eventsRequest = EventsRequestBuilder.aEventRequest()
                .addSensorId("TEMP01")
                .addSensorId("TEMP02")
                .withAckState(AckState.PENDING_ACK)
                .withActivation(ActivationState.ACTIVE)
                .build();


        List<Event> eventsList = apiClient.getEvents(eventsRequest);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertThat(recordedRequest.getPath(), equalTo("/v1/events"));
        assertThat(recordedRequest.getMethod(), equalTo("POST"));

        Event event = eventsList.get(0);
        assertThat(event.getDeactivationTime(), equalTo("21/05/2015 06:41:29"));
        assertThat(event.getVariableName(), equalTo("TEMP"));
        assertThat(event.getName(), equalTo("INACTIVITY_EVENT"));
        assertThat(event.getData(), equalTo("Se ha superado el tiempo máximo sin recibir medidas (1220 segundos) en variable TEMP"));
        assertThat(event.getSensorId(), equalTo("TEMP01"));
        assertTrue(event.isActive());
        assertThat(event.getType(), equalTo(EventType.ALARM));
        assertThat(event.getAckState(), equalTo(AckState.NOT_REQUIRED));
        
    }

    @Test
    public void countEvents() throws Exception {
        enqueueResponse("/count_events.json");

        EventsRequest eventsRequest = EventsRequestBuilder.aEventRequest()
                .addSensorId("TEMP01")
                .addSensorId("TEMP02")
                .withAckState(AckState.PENDING_ACK)
                .withActivation(ActivationState.ACTIVE)
                .build();

        Integer count = apiClient.countEvents(eventsRequest);

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertThat(recordedRequest.getPath(), equalTo("/v1/events/count"));
        assertThat(recordedRequest.getMethod(), equalTo("POST"));
        assertThat(count, equalTo(121));

    }

    @Test(expected = AuthenticationException.class)
    public void unauthorizedHttpStatusThrowsAuthenticationException() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(UNAUTHORIZED_HTTP_STATUS));

        apiClient.getSensors();
    }

    @Test(expected = UserCanNotAccessException.class)
    public void userCanNotRequest() throws Exception {

        enqueueResponse("/userCanNotRequest.json");

        apiClient.getSensors();

    }

    private void enqueueResponse(String resource) throws URISyntaxException, IOException {
        String jsonResponse = TestUtils.readFullFile(resource);

        mockWebServer.enqueue(new MockResponse().setBody(jsonResponse));
    }

    private HttpUrl getHttpUrl(RecordedRequest recordedRequest) {
        return HttpUrl.parse("http://" + mockWebServer.getHostName() + recordedRequest.getPath());
    }

}
