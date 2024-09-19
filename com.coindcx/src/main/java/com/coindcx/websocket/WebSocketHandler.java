package com.coindcx.websocket;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // Called when a new WebSocket connection is established
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection established: " + session.getId());

        // Example: Send a message to subscribe to a CoinDCX trading pair
        String subscribeMessage = "{\"event\": \"subscribe\", \"channel\": \"BTC/USDT\"}";
        session.sendMessage(new TextMessage(subscribeMessage));
    }

    // Called when a message is received from the WebSocket
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received WebSocket message: " + message.getPayload());

        // Example: Handle real-time data and process accordingly
        String payload = message.getPayload();
        
        // Example trigger: If the market price falls below a certain value, simulate a buy order
        if (payload.contains("price")) {
            // Parse the JSON payload and check for trigger conditions
            double price = parsePriceFromPayload(payload);

            double triggerPrice = 50000.0; // Set your trigger price here
            if (price <= triggerPrice) {
                String buyOrderPayload = prepareBuyOrderPayload();
                System.out.println("Trigger hit: Sending buy order payload: " + buyOrderPayload);
            }
        }
    }

    // Called when an error occurs on the WebSocket
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("WebSocket transport error: " + exception.getMessage());
        exception.printStackTrace();
    }

    // Called when the WebSocket connection is closed
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        System.out.println("WebSocket connection closed: " + session.getId() + " Status: " + status);
    }

    // Helper method to parse price from the incoming JSON message
    private double parsePriceFromPayload(String payload) {
        // Implement the JSON parsing logic to extract price data
        // For example, using Gson or Jackson libraries to parse the incoming message
        // This is just a placeholder, you need to adjust this based on the actual payload structure
        return 49000.0; // Example value
    }

    // Helper method to prepare the buy order payload
    private String prepareBuyOrderPayload() {
        // Create the payload structure for a simulated buy order
        // You would construct this payload based on CoinDCX API requirements
        return "{\"event\": \"order\", \"side\": \"buy\", \"price\": \"50000\", \"quantity\": \"0.1\", \"pair\": \"BTC/USDT\"}";
    }
}
