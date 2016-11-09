package com.iotsens.sdk.events;

import java.util.List;

public class EventsRequestBuilder {

    private EventsRequest eventsRequest;

    public static EventsRequestBuilder aEventRequest() {
        return new EventsRequestBuilder();
    }

    private EventsRequestBuilder() {
        eventsRequest = new EventsRequest();
    }

    public EventsRequestBuilder addSensorId(String sensorId) {
        eventsRequest.addSensorId(sensorId);
        return this;
    }

    public EventsRequestBuilder withVariableName(String variableName) {
        eventsRequest.setVariableName(variableName);
        return this;
    }

    public EventsRequestBuilder withFrom(String from) {
        eventsRequest.setFrom(from);
        return this;
    }

    public EventsRequestBuilder withUntil(String until) {
        eventsRequest.setUntil(until);
        return this;
    }

    public EventsRequestBuilder withName(String name) {
        eventsRequest.setName(name);
        return this;
    }

    public EventsRequestBuilder withActivation(ActivationState activation) {
        eventsRequest.setActivation(activation);
        return this;
    }

    public EventsRequestBuilder withAckState(AckState ackState) {
        eventsRequest.setAckState(ackState);
        return this;
    }

    public EventsRequestBuilder withEventType(EventType eventType) {
        eventsRequest.setEventType(eventType);
        return this;
    }

    public EventsRequestBuilder withOffset(Integer offset) {
        eventsRequest.setOffset(offset);
        return this;
    }

    public EventsRequestBuilder withLimit(Integer limit) {
        eventsRequest.setLimit(limit);
        return this;
    }

    public EventsRequest build() {
        return eventsRequest;
    }
}