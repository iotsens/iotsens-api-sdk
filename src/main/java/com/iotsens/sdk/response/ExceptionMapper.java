package com.iotsens.sdk.response;

import com.iotsens.sdk.exceptions.FailedCallException;
import com.iotsens.sdk.exceptions.ObjectNotFoundException;
import com.iotsens.sdk.exceptions.UserCanNotAccessException;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper {

    private static Map<String, RuntimeException> mappings = new HashMap<String, RuntimeException>() {{
        put("com.grupogimeno.maya.exceptions.UserCanNotRequestException", new UserCanNotAccessException());
        put("com.grupogimeno.maya.iot.exceptions.ObjectNotFoundException", new ObjectNotFoundException());
    }};


    public RuntimeException map(String exceptionRepresentation) {
        if (!mappings.containsKey(exceptionRepresentation)) return new FailedCallException();

        return mappings.get(exceptionRepresentation);
    }
}
