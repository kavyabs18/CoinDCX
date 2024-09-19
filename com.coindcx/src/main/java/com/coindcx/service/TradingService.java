package com.coindcx.service;

import com.coindcx.model.OrderPayload;
import org.springframework.stereotype.Service;

@Service
public class TradingService {

    public OrderPayload prepareBuyOrder(double price, String currencyPair) {
        return new OrderPayload("buy", "0.1", String.valueOf(price), currencyPair);
    }

    public OrderPayload prepareSellOrder(double price, String currencyPair) {
        return new OrderPayload("sell", "0.1", String.valueOf(price), currencyPair);
    }

    public OrderPayload prepareCancelOrder(String orderId) {
        return new OrderPayload("cancel", orderId, null, null);
    }
}
