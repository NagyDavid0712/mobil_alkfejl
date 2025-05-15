package com.nd.cashflow.model;

public class Company {

    private String name;
    private String country;
    private String exchange;
    private String weburl;
    private String finnhubIndustry;
    private String logo;

    public Company(String name, String symbol, String country, String exchange, String weburl, String finnhubIndustry, String logo) {
        this.name = name;
        this.country = country;
        this.exchange = exchange;
        this.weburl = weburl;
        this.finnhubIndustry = finnhubIndustry;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getExchange() {
        return exchange;
    }

    public String getWeburl() {
        return weburl;
    }

    public String getFinnhubIndustry() {
        return finnhubIndustry;
    }

    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public void setFinnhubIndustry(String finnhubIndustry) {
        this.finnhubIndustry = finnhubIndustry;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
