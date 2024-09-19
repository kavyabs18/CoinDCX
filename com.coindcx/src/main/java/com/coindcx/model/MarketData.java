package com.coindcx.model;

public class MarketData {
    private String currencyPair;
    private double currentPrice;
    public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public MarketData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MarketData(String currencyPair, double currentPrice) {
		super();
		this.currencyPair = currencyPair;
		this.currentPrice = currentPrice;
	}

    // Getters, Setters, Constructors
}
