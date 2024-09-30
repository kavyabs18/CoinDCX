package com.coindcx.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.coindcx.model.OrderPayload;
import com.coindcx.service.TradingService;
import org.json.JSONObject;

public class CoinDCXWebSocketClient extends WebSocketClient {

    private final String currencyPair;
    private final double triggerPrice;
    private final TradingService tradingService; // Reference to TradingService

    public CoinDCXWebSocketClient(String currencyPair, double triggerPrice, TradingService tradingService) throws URISyntaxException {
        super(new URI("wss://api.coindcx.com/marketdata"));
        this.currencyPair = currencyPair;
        this.triggerPrice = triggerPrice;
        this.tradingService = tradingService; // Initialize TradingService
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to CoinDCX WebSocket API");

        // Subscribe to the ticker channel for the specified currency pair
        String subscribeMessage = String.format(
            "{\"type\":\"subscribe\",\"channels\":[{\"name\":\"ticker\",\"pair\":\"%s\"}]}",
            currencyPair
        );
        this.send(subscribeMessage);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);

        // Parse the message to get the current market price
        JSONObject jsonMessage = new JSONObject(message);
        double marketPrice = jsonMessage.getJSONObject("data").getDouble("last_price"); // Adjust based on actual JSON structure

        // Check if the market price hits or falls below the trigger price for a buy order
        if (marketPrice <= triggerPrice) {
            // Prepare the buy order payload
            OrderPayload buyPayload = tradingService.prepareBuyOrder(marketPrice, currencyPair);
            System.out.println("Prepared buy order payload: " + buyPayload);
            // Optionally, execute the buy order here
        }

        // You can also implement similar logic for a sell order
        // if (marketPrice >= triggerPrice) {
        //     OrderPayload sellPayload = tradingService.prepareSellOrder(marketPrice, currencyPair);
        //     System.out.println("Prepared sell order payload: " + sellPayload);
        //     // Optionally, execute the sell order here
        // }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
        ex.printStackTrace();
    }
}