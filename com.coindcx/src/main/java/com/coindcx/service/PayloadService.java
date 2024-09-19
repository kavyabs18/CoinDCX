package com.coindcx.service;

import com.google.gson.Gson;
import com.coindcx.model.OrderPayload;
import org.springframework.stereotype.Service;

@Service
public class PayloadService {
    private final Gson gson = new Gson();

    public String preparePayloadAsString(OrderPayload payload) {
        return gson.toJson(payload);
    }
}
