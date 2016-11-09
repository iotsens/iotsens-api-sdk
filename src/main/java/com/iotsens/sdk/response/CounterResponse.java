package com.iotsens.sdk.response;

public class CounterResponse {

    private boolean success;
    private Counter data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Counter getData() {
        return data;
    }

    public void setData(Counter data) {
        this.data = data;
    }
}
