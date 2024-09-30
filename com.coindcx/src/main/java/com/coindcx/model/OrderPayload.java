package com.coindcx.model;

public class OrderPayload {
    private String orderType; // buy, sell, cancel
    private String amount; // Order amount (e.g., "0.1")
    private double price; // Order price
    private String currencyPair; // Currency pair (e.g., "BTCUSDT")
    private String orderId; // Optional: ID for tracking orders

    // Constructor for buy/sell orders
    public OrderPayload(String orderType, String amount, double price, String currencyPair) {
        this.orderType = orderType;
        this.amount = amount;
        this.price = price;
        this.currencyPair = currencyPair;
    }

    // Constructor for cancel orders (if you want to include orderId)
    public OrderPayload(String orderType, String orderId, String currencyPair) {
        this.orderType = orderType;
        this.orderId = orderId;
        this.currencyPair = currencyPair;
    }

    // Getters and Setters (if needed)
    public String getOrderType() {
        return orderType;
    }

    public String getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public String getOrderId() {
        return orderId;
    }

    // Optionally, implement a toString() method for easier logging
    @Override
    public String toString() {
        return "OrderPayload{" +
                "orderType='" + orderType + '\'' +
                ", amount='" + amount + '\'' +
                ", price=" + price +
                ", currencyPair='" + currencyPair + '\'' +
                (orderId != null ? ", orderId='" + orderId + '\'' : "") +
                '}';
    }
}
