package com.coindcx.websocket;

import org.springframework.stereotype.Component;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class CoinDCXWebSocketClient extends WebSocketClient {

    public CoinDCXWebSocketClient() throws URISyntaxException {
        super(new URI("wss://coindcx.com/markets"));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to CoinDCX WebSocket API");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        // Handle message and forward to TradingService
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
