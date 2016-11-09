package com.iotsens.sdk;


import com.iotsens.sdk.events.Event;
import com.iotsens.sdk.events.EventsRequest;
import com.iotsens.sdk.exceptions.ConnectionProblemException;
import com.iotsens.sdk.exceptions.FailedCallException;
import com.iotsens.sdk.measures.Measure;
import com.iotsens.sdk.measures.MeasuresRequest;
import com.iotsens.sdk.measures.MultiSensorMeasuresRequest;
import com.iotsens.sdk.measures.rangemeasures.RangeMeasure;
import com.iotsens.sdk.measures.rangemeasures.RangeMeasuresRequest;
import com.iotsens.sdk.request.RequestBodyBuilder;
import com.iotsens.sdk.sensors.*;
import okhttp3.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.iotsens.sdk.request.OKHttpUrlBuilder.aHttpUrl;
import static com.iotsens.sdk.sensors.SensorsRequestBuilder.aSensorRequest;

public class IoTSensApiClient {

    private String baseUrl;
    private Clock clock;
    private final Credentials credentials;
    private ResponseMapper responseMapper;

    IoTSensApiClient(Clock clock, Credentials credentials, String baseUrl, ResponseMapper responseMapper) {
        this.clock = clock;
        this.credentials = credentials;
        this.baseUrl = baseUrl;
        this.responseMapper = responseMapper;
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     */
    public List<SensorBasic> getSensors() {
        return getSensors(aSensorRequest().build());
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     */
    public List<SensorBasic> getSensors(SensorsRequest sensorRequest) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = getHttpUrl("sensors", sensorRequest);
        Request request = getAuthenticatedRequest(url).build();

        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToListOfSensorBasic(response);

        } catch (IOException e) {
            throw new ConnectionProblemException();
        }
    }

    private String calculateMD5(Long currentTimestamp) {
        MessageDigest md5Generator;
        try {
            md5Generator = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new FailedCallException();
        }

        String preToken = currentTimestamp + credentials.getSecret() + credentials.getApp();
        byte[] md5 = md5Generator.digest(preToken.getBytes());

        BigInteger bigInteger = new BigInteger(1, md5);
        return bigInteger.toString(16);
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public List<SensorBasicWithProperties> getSensors(List<String> sensorIds)  {
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for (String sensorId: sensorIds) {
            formBodyBuilder.add("sensorId", sensorId);
        }

        RequestBody requestBody = formBodyBuilder.build();

        HttpUrl url = HttpUrl.parse(baseUrl + "sensors/info");
        Request request = getAuthenticatedRequest(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToListOfSensorBasicWithProperties(response);

        } catch (IOException e) {
            throw new ConnectionProblemException();
        }

    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public SensorWithPropertiesAndVariables getSensor(String sensorId) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(baseUrl + "sensors/"+sensorId);
        Request request = getAuthenticatedRequest(url).build();

        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToSensorWithPropertiesandVariables(response);

        } catch (IOException e) {
            throw new ConnectionProblemException();
        }
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public List<Measure> getMeasures(MeasuresRequest measuresRequest) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = getHttpUrl("sensors/" + measuresRequest.getSensorId() + "/variables/" + measuresRequest.getVariableName() + "/measures", measuresRequest);
        Request request = getAuthenticatedRequest(url).build();

        return getMeasures(client, request);
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public List<Measure> getMeasures(MultiSensorMeasuresRequest measuresRequest) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = getRequestBody(measuresRequest);

        HttpUrl url = HttpUrl.parse(baseUrl + "measures");
        Request request = getAuthenticatedRequest(url)
                .post(requestBody)
                .build();

        return getMeasures(client, request);
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public List<RangeMeasure> getRangeMeasures(RangeMeasuresRequest measuresRequest) {
        OkHttpClient client = new OkHttpClient();

        Request request;
        if (measuresRequest.hasSingleSensorId()) {
            HttpUrl url = getHttpUrl("sensors/" + measuresRequest.getSensorIds().get(0) + "/variables/" + measuresRequest.getVariableName() + "/rangemeasures", measuresRequest);
            request = getAuthenticatedRequest(url).build();
        } else {
            RequestBody requestBody = getRequestBody(measuresRequest);

            HttpUrl url = HttpUrl.parse(baseUrl + "rangemeasures");
            request = getAuthenticatedRequest(url)
                    .post(requestBody)
                    .build();
        }
        return getRangeMeasures(client, request);
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public List<Event> getEvents(EventsRequest eventsRequest) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = getRequestBody(eventsRequest);

        HttpUrl url = HttpUrl.parse(baseUrl + "events");
        Request request = getAuthenticatedRequest(url)
                .post(requestBody)
                .build();

        return getEvents(client, request);
    }

    /*
     * @throws com.iotsens.sdk.exceptions.UserCanNotAccessException
     * @throws com.iotsens.sdk.exceptions.FailedCallException
     * @throws com.iotsens.sdk.exceptions.ConnectionProblemException
     * @throws com.iotsens.sdk.exceptions.AuthenticationException
     * @throws com.iotsens.sdk.exceptions.ObjectNotFoundException
     */
    public Integer countEvents(EventsRequest eventsRequest) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = getRequestBody(eventsRequest);

        HttpUrl url = HttpUrl.parse(baseUrl + "events/count");
        Request request = getAuthenticatedRequest(url)
                .post(requestBody)
                .build();

        return countEvents(client, request);
    }

    private Integer countEvents(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToCount(response);
        } catch (IOException e) {
            throw new ConnectionProblemException(e);
        }
    }

    private List<Event> getEvents(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToEvents(response);

        } catch (IOException e) {
            throw new ConnectionProblemException(e);
        }
    }



    private RequestBody getRequestBody(MultiSensorMeasuresRequest measuresRequest) {
        RequestBodyBuilder builder = RequestBodyBuilder.aRequestBody()
            .addParameter("variableName", measuresRequest.getVariableName())
            .addParameter("limit", measuresRequest.getLimit())
            .addParameter("until", measuresRequest.getUntil())
            .addParameter("from", measuresRequest.getFrom());

        for (String sensorId: measuresRequest.getSensorIds()) {
            builder.addParameter("sensorId", sensorId);
        }

        return builder.build();
    }

    private RequestBody getRequestBody(RangeMeasuresRequest measuresRequest) {

        RequestBodyBuilder builder = RequestBodyBuilder.aRequestBody()
                .addParameter("variableName", measuresRequest.getVariableName())
                .addParameter("until", measuresRequest.getUntil())
                .addParameter("from", measuresRequest.getFrom())
                .addParameter("rangeUntil", measuresRequest.getRangeUnit())
                .addParameter("summaryOperation", measuresRequest.getSummaryOperation())
                .addParameter("unit", measuresRequest.getUnit());

        for (String sensorId: measuresRequest.getSensorIds()) {
            builder.addParameter("sensorId", sensorId);
        }

        return builder.build();

    }

    private RequestBody getRequestBody(EventsRequest eventsRequest) {
        RequestBodyBuilder builder = RequestBodyBuilder.aRequestBody()
                .addParameter("variableName", eventsRequest.getVariableName())
                .addParameter("until", eventsRequest.getUntil())
                .addParameter("from", eventsRequest.getFrom())
                .addParameter("limit", eventsRequest.getLimit())
                .addParameter("name", eventsRequest.getName())
                .addParameter("ackstate", eventsRequest.getAckState())
                .addParameter("activation", eventsRequest.getActivation())
                .addParameter("eventType", eventsRequest.getEventType())
                .addParameter("offset",  eventsRequest.getOffset());

        for (String sensorId: eventsRequest.getSensorIds()) {
            builder.addParameter("sensorId", sensorId);
        }

        return builder.build();
    }


    private List<Measure> getMeasures(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToMeasures(response);

        } catch (IOException e) {
            throw new ConnectionProblemException(e);
        }
    }

    private List<RangeMeasure> getRangeMeasures(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
            return responseMapper.mapToRangeMeasures(response);

        } catch (IOException e) {
            throw new ConnectionProblemException(e);
        }
    }

    private Request.Builder getAuthenticatedRequest(HttpUrl url) {
        Long currentTimestamp = clock.getCurrentTimestamp();

        String md5String = calculateMD5(currentTimestamp);
        return new Request.Builder()
                .url(url)
                .addHeader("GG-Requester-Application", credentials.getApp())
                .addHeader("GG-Request-Timestamp", currentTimestamp.toString())
                .addHeader("GG-Request-Signature", md5String)
                .addHeader("IOT-Authorized-User", credentials.getUser());
    }

    private HttpUrl getHttpUrl(String path, SensorsRequest request) {

        return aHttpUrl(baseUrl + path)
                .addQueryParameter("north", request.getNorth())
                .addQueryParameter("south", request.getSouth())
                .addQueryParameter("east", request.getEast())
                .addQueryParameter("west", request.getWest())
                .addQueryParameter("limit", request.getLimit())
                .addQueryParameter("offset", request.getOffset())
                .addQueryParameter("query", request.getQuery())
                .addQueryParameter("categoryId", request.getCategoryId())
                .addQueryParameter("templateId", request.getTemplateId())
                .build();

    }

    private HttpUrl getHttpUrl(String path, MeasuresRequest request) {

        return aHttpUrl(baseUrl + path)
                .addQueryParameter("from", request.getFrom())
                .addQueryParameter("until", request.getUntil())
                .addQueryParameter("limit", request.getLimit())
                .addQueryParameter("dayParam", request.getDayParam())
                .addQueryParameter("daysBeforeMargin", request.getDaysBeforeMargin())
                .addQueryParameter("daysAfterMargin", request.getDaysAfterMargin())
                .build();

    }

    private HttpUrl getHttpUrl(String path, RangeMeasuresRequest request) {

        return aHttpUrl(baseUrl + path)
                .addQueryParameter("from", request.getFrom())
                .addQueryParameter("until", request.getUntil())
                .addQueryParameter("rangeUnit", request.getRangeUnit())
                .addQueryParameter("summaryOperation", request.getSummaryOperation().toString())
                .addQueryParameter("unit", request.getUnit())
                .build();

    }



}