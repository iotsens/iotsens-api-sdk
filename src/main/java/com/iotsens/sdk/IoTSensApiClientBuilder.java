package com.iotsens.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotsens.sdk.jackson.CustomJacksonMapper;
import com.iotsens.sdk.response.ExceptionMapper;
import com.iotsens.sdk.sensors.ResponseMapper;

public class IoTSensApiClientBuilder {

   public static final String IOTSENS_BASE_URL = "http://api.iotsens.com/v1/";

    private String app;
    private String secret;
    private String user;

    public static IoTSensApiClientBuilder aIoTSensClient() {
        return new IoTSensApiClientBuilder();
    }

    private IoTSensApiClientBuilder() {}

    public IoTSensApiClientBuilder withDefaultUser(String user) {
        this.user = user;
        return this;
    }

    public IoTSensApiClientBuilder withApplication(String applicationId) {
        this.app = applicationId;
        return this;
    }

    public IoTSensApiClientBuilder withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public IoTSensApiClient build() {
        Credentials credentials = new Credentials(app, secret, user);
        ResponseMapper responseMapper = new ResponseMapper();
        return new IoTSensApiClient(new SystemClock(), credentials, IOTSENS_BASE_URL, responseMapper);
    }
}
