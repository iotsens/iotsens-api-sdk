package com.iotsens.sdk.request;

import okhttp3.HttpUrl;

public class OKHttpUrlBuilder {

    private HttpUrl.Builder httpBuilder;

    public static OKHttpUrlBuilder aHttpUrl(String URI) {
        return new OKHttpUrlBuilder(URI);
    }

    private OKHttpUrlBuilder(String URI) {
        httpBuilder = HttpUrl.parse(URI).newBuilder();
    }

    public OKHttpUrlBuilder addQueryParameter(String key, Object value) {
        if (value == null) return this;

        httpBuilder = httpBuilder.addQueryParameter(key, value.toString());

        return this;
    }

    public HttpUrl build() {
        return httpBuilder.build();
    }
}
