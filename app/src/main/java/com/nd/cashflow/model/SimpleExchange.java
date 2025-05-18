package com.nd.cashflow.model;

public class SimpleExchange {
    private String iso;
    private String name;
    private double eurPrice;
    private double usdPrice;

    public SimpleExchange(String iso, String name, double eurPrice, double usdPrice) {
        this.iso = iso;
        this.name = name;
        this.eurPrice = eurPrice;
        this.usdPrice = usdPrice;
    }

    public String getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }

    public double getEurPrice() {
        return eurPrice;
    }

    public double getUsdPrice() {
        return usdPrice;
    }
}
