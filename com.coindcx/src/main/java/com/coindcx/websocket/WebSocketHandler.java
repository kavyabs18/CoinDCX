package com.coindcx.websocket;

import com.coindcx.model.OrderPayload;
import com.coindcx.service.TradingService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private double triggerPrice;
    private String currencyPair;
    private final TradingService tradingService;

    public WebSocketHandler(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    public void setTriggerPrice(double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection established: " + session.getId());

        // Send subscription message for real-time market data
        String subscribeMessage = "{\"event\": \"subscribe\", \"channel\": \"market\", \"pair\": \"" + currencyPair + "\"}";
        session.sendMessage(new TextMessage(subscribeMessage));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received WebSocket message: " + message.getPayload());

        // Parse the message and check the price
        double marketPrice = parsePriceFromPayload(message.getPayload());

        if (marketPrice >= triggerPrice) {
            System.out.println("Market price reached the trigger. Placing a buy order.");
            // Prepare and print buy order payload
            OrderPayload buyPayload = tradingService.prepareBuyOrder(triggerPrice, currencyPair);
            System.out.println("Prepared buy order: " + buyPayload);
        }
    }

    private double parsePriceFromPayload(String payload) {
        // Implement actual JSON parsing logic to extract price data from CoinDCX's market feed
        // Example placeholder value:
        return 50000.0;
    }
}
