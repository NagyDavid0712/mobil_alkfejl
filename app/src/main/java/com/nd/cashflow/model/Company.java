package com.nd.cashflow.model;

public class Company {

    private String name;
    private String symbol;
    private String country;
    private String exchange;
    private String url;
    private String industry;
    private String image;

    public Company(String name, String symbol, String country, String exchange, String url, String industry, String image) {
        this.name = name;
        this.symbol = symbol;
        this.country = country;
        this.exchange = exchange;
        this.url = url;
        this.industry = industry;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCountry() {
        return country;
    }

    public String getExchange() {
        return exchange;
    }

    public String getUrl() {
        return url;
    }

    public String getIndustry() {
        return industry;
    }

    public String getImage() {
        return image;
    }
}
