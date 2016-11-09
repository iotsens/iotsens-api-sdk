package com.iotsens.sdk.sensors;

import com.iotsens.sdk.request.RequestBodyBuilder;

public class SensorsRequestBuilder {

    SensorsRequest sensorsRequest;

    public static SensorsRequestBuilder aSensorRequest() {
        return new SensorsRequestBuilder();
    }

    private SensorsRequestBuilder() {
        sensorsRequest = new SensorsRequest();
    }

    public SensorsRequestBuilder withNorth(Double north) {
        sensorsRequest.setNorth(north);
        return this;
    }

    public SensorsRequestBuilder withSouth(Double south) {
        sensorsRequest.setSouth(south);
        return this;
    }

    public SensorsRequestBuilder withEast(Double east) {
        sensorsRequest.setEast(east);
        return this;
    }

    public SensorsRequestBuilder withWest(Double west) {
        sensorsRequest.setWest(west);
        return this;
    }

    public SensorsRequestBuilder withQuery(String query) {
        sensorsRequest.setQuery(query);
        return this;
    }

    public SensorsRequestBuilder withLimit(int limit) {
        sensorsRequest.setLimit(limit);
        return this;
    }

    public SensorsRequestBuilder withOffset(int offset) {
        sensorsRequest.setOffset(offset);
        return this;
    }

    public SensorsRequestBuilder withCategoryId(int categoryId) {
        sensorsRequest.setCategoryId(categoryId);
        return this;
    }

    public SensorsRequestBuilder withTemplateId(int templateId) {
        sensorsRequest.setTemplateId(templateId);
        return this;
    }

    public SensorsRequest build() {
        return sensorsRequest;
    }


}
