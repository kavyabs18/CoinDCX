package com.coindcx.model;

public class OrderPayload {
    private String orderType;
    private String amount;
    private String price;
    private String currencyPair;
    public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public OrderPayload(String orderType, String amount, String price, String currencyPair) {
		super();
		this.orderType = orderType;
		this.amount = amount;
		this.price = price;
		this.currencyPair = currencyPair;
	}
	public OrderPayload() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters, Setters, Constructors
}
