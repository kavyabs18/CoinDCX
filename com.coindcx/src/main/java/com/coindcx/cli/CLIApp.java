package com.coindcx.cli;

import com.coindcx.model.OrderPayload;
import com.coindcx.service.PayloadService;
import com.coindcx.service.TradingService;
import com.coindcx.websocket.CoinDCXWebSocketClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Scanner;

@Component
public class CLIApp implements CommandLineRunner {

    private final TradingService tradingService;
    private final PayloadService payloadService;

    public CLIApp(TradingService tradingService, PayloadService payloadService) {
        this.tradingService = tradingService;
        this.payloadService = payloadService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter trigger price: ");
        double triggerPrice = scanner.nextDouble();
        System.out.print("Enter currency pair (e.g., BTCUSDT): ");
        String currencyPair = scanner.next();

        // Prepare the buy order payload using the instance of TradingService
        OrderPayload buyPayload = tradingService.prepareBuyOrder(triggerPrice, currencyPair);
        Thread.sleep(5000);
        System.out.println("Prepared to buy order payload: " + payloadService.preparePayloadAsString(buyPayload));

        // Simulate order cancellation
        String orderId = "123456"; // Example order ID for cancellation
        OrderPayload cancelPayload = tradingService.prepareCancelOrder(orderId, currencyPair);
        Thread.sleep(5000);
        System.out.println("Prepared to cancel order payload: " + payloadService.preparePayloadAsString(cancelPayload));

        // Initialize the WebSocket client
        try {
            CoinDCXWebSocketClient webSocketClient = new CoinDCXWebSocketClient(currencyPair, triggerPrice, tradingService);
            webSocketClient.connect(); // Connect to WebSocket
        } catch (URISyntaxException e) {
            System.err.println("Invalid WebSocket URI: " + e.getMessage());
        }
    }
} 