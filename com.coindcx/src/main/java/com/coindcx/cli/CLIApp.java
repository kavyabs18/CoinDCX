package com.coindcx.cli;

import com.coindcx.model.OrderPayload;
import com.coindcx.service.PayloadService;
import com.coindcx.service.TradingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

		// Simulate payload creation
		OrderPayload buyPayload = tradingService.prepareBuyOrder(triggerPrice, currencyPair);
		System.out.println("Prepared buy order payload: " + payloadService.preparePayloadAsString(buyPayload));

		// WebSocket can be initiated here to start real-time monitoring
	}
}
