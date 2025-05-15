package com.nd.cashflow.modules;

import com.nd.cashflow.model.Company;
import com.nd.cashflow.model.Crypto;

import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;

public class CFApiWrapper {

    private String COINGECKO_API_KEY = "CG-XkaYUd8MLTwDHdSsWShbQTUc";
    String[] tickers = {
            "AAPL",        // Apple Inc.
            "MSFT",        // Microsoft Corp.
            "2222.SR",     // Saudi Aramco
            "GOOGL",       // Alphabet Inc.
            "AMZN",        // Amazon.com Inc.
            "NVDA",        // NVIDIA Corp.
            "BRK-B",       // Berkshire Hathaway
            "META",        // Meta Platforms
            "TSLA",        // Tesla Inc.
            "TSM",         // Taiwan Semiconductor
            "LLY",         // Eli Lilly & Co.
            "JNJ",         // Johnson & Johnson
            "JPM",         // JPMorgan Chase
            "XOM",         // ExxonMobil
            "V",           // Visa Inc.
            "WMT",         // Walmart
            "005930.KS",   // Samsung Electronics
            "NESN.SW",     // Nestlé
            "0700.HK",     // Tencent
            "MC.PA"        // LVMH
    };

    private static CFApiWrapper instance;

    private CFApiWrapper() {  }

    public CFApiWrapper getInstance() {
        if (instance == null) {
            instance = new CFApiWrapper();
        }

        return instance;
    }

    public List<Crypto> getCryptos() {
        return null;
    }

    public ArrayList<Company> getCompanys() { return null; }

}
