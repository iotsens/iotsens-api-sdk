package com.iotsens.sdk.response;

import java.util.HashMap;
import java.util.Map;

public class FailedResponse {

    private String msg;
    private Map<String, String> data = new HashMap<String, String>();

    public String getMsg() {
        return msg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public String getExceptionRepresentation() {
        return data.get("exceptionClassName");
    }

}
