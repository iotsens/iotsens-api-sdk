package com.iotsens.sdk.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtils {

    public static String readFullFile(String resource) throws URISyntaxException, IOException {
        URI responseFileURI = TestUtils.class.getResource(resource).toURI();
        return new String(Files.readAllBytes(Paths.get(responseFileURI)), StandardCharsets.UTF_8);
    }
}
