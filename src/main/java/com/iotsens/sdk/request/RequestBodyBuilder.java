package com.iotsens.sdk.request;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestBodyBuilder {

    FormBody.Builder formBodyBuilder;

    public static RequestBodyBuilder aRequestBody() {
        return new RequestBodyBuilder();
    }

    private RequestBodyBuilder() {
        formBodyBuilder = new FormBody.Builder();
    }

    public RequestBodyBuilder addParameter(String key, Object value) {
        if (value == null) return this;

        formBodyBuilder.add(key,String.valueOf(value));
        return this;
    }

    public RequestBody build() {
        return formBodyBuilder.build();
    }

}
