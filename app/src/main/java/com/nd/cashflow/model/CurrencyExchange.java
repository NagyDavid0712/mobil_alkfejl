package com.nd.cashflow.model;

public class CurrencyExchange {
    private double eur;
    private double usd;
    private double gbp;

    public CurrencyExchange(double eur, double usd, double gbp) {
        this.eur = 1 / eur;
        this.usd = 1 / usd;
        this.gbp = 1 / gbp;
    }

    public double getEur() {
        return eur;
    }

    public double getUsd() {
        return usd;
    }

    public double getGbp() {
        return gbp;
    }
}
