package com.nd.cashflow.modules;

import com.nd.cashflow.model.CurrencyExchange;

public interface CFDataCurrencyExchangeCallback {
    void onDataReady(CurrencyExchange data);
    void onError(Exception ex);
}
