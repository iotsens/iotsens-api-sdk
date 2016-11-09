package com.iotsens.sdk.sensors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotsens.sdk.events.Event;
import com.iotsens.sdk.exceptions.AuthenticationException;
import com.iotsens.sdk.jackson.CustomJacksonMapper;
import com.iotsens.sdk.measures.Measure;
import com.iotsens.sdk.measures.rangemeasures.RangeMeasure;
import com.iotsens.sdk.response.*;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ResponseMapper {

    private ObjectMapper objectMapper = new CustomJacksonMapper();
    private ExceptionMapper exceptionMapper = new ExceptionMapper();

    public List<SensorBasic> mapToListOfSensorBasic(Response response) throws IOException {
        return (List<SensorBasic>) mapToListResponse(response, SensorBasic.class);
    }

    public List<SensorBasicWithProperties> mapToListOfSensorBasicWithProperties(Response response) throws IOException {
        return (List<SensorBasicWithProperties>) mapToListResponse(response, SensorBasicWithProperties.class);
    }

    public SensorWithPropertiesAndVariables mapToSensorWithPropertiesandVariables(Response response) throws IOException {
        if (401 == response.code()) throw new AuthenticationException();

        String body = response.body().string();
        BasicResponse basicResponse = objectMapper.readValue(body, BasicResponse.class);

        if (basicResponse.isSuccess()) {
            IoTSensResponseSensorWithPropertiesAndVariables ioTSensResponse = objectMapper.readValue(body, IoTSensResponseSensorWithPropertiesAndVariables.class);
            return ioTSensResponse.getData();
        } else {
            FailedResponse failedResponse = objectMapper.readValue(body, FailedResponse.class);
            throw exceptionMapper.map(failedResponse.getExceptionRepresentation());
        }
    }

    public List<Measure> mapToMeasures(Response response) throws IOException {
        return (List<Measure>) mapToListResponse(response, Measure.class);
    }

    public List<RangeMeasure> mapToRangeMeasures(Response response) throws IOException {
        return (List<RangeMeasure>) mapToListResponse(response, RangeMeasure.class);
    }

    public List<Event> mapToEvents(Response response) throws IOException {
        return (List<Event>) mapToListResponse(response, Event.class);
    }

    public Integer mapToCount(Response response) throws IOException {
        if (401 == response.code()) throw new AuthenticationException();

        String body = response.body().string();
        BasicResponse basicResponse = objectMapper.readValue(body, BasicResponse.class);

        if (basicResponse.isSuccess()) {
            CounterResponse ioTSensResponse = objectMapper.readValue(body, CounterResponse.class);
            return ioTSensResponse.getData().getValue();
        } else {
            FailedResponse failedResponse = objectMapper.readValue(body, FailedResponse.class);
            throw exceptionMapper.map(failedResponse.getExceptionRepresentation());
        }
    }

    private List<?> mapToListResponse(Response response, Class<?> responseClass) throws IOException {
        if (401 == response.code()) throw new AuthenticationException();

        String body = response.body().string();
        BasicResponse basicResponse = objectMapper.readValue(body, BasicResponse.class);

        if (basicResponse.isSuccess()) {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(IoTSensResponse.class, responseClass);
            IoTSensResponse<?> ioTSensResponse = objectMapper.readValue(body, javaType);
            return ioTSensResponse.getData();
        } else {
            FailedResponse failedResponse = objectMapper.readValue(body, FailedResponse.class);
            throw exceptionMapper.map(failedResponse.getExceptionRepresentation());
        }
    }


}