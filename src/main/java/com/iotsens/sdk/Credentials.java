package com.iotsens.sdk;

public class Credentials {

    private final String app;
    private final String secret;
    private final String user;

    public Credentials(String app, String secret, String user) {
        this.app = app;
        this.secret = secret;
        this.user = user;
    }


    public String getApp() {
        return app;
    }

    public String getUser() {
        return user;
    }

    public String getSecret() {
        return secret;
    }
}
