package com.bookstore.dto;

import java.util.Map;

public class CallbackRequest {
    private Map<String, String> data;
    private String signature;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}