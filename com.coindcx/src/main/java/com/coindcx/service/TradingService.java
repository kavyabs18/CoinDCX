package com.coindcx.service;

import com.coindcx.model.OrderPayload;
import org.springframework.stereotype.Service;

@Service
public class TradingService {

    // Method to prepare a buy order
    public OrderPayload prepareBuyOrder(double price, String currencyPair) {
        return new OrderPayload("buy", "0.1", price, currencyPair); // Adjust amount as needed
    }

    // Method to prepare a sell order
    public OrderPayload prepareSellOrder(double price, String currencyPair) {
        return new OrderPayload("sell", "0.1", price, currencyPair); // Adjust amount as needed
    }

    // Method to prepare a cancellation order
    public OrderPayload prepareCancelOrder(String orderId, String currencyPair) {
        return new OrderPayload("cancel", orderId, currencyPair); // Provide order ID for cancellation
    }
}
