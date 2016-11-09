package com.iotsens.sdk.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJacksonMapper extends ObjectMapper {

    public CustomJacksonMapper() {
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
