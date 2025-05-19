package com.nd.cashflow.modules;

import com.nd.cashflow.model.SimpleExchange;

public interface CFDataExchangeCallback {
    void onDataready(SimpleExchange data);
    void onError(Exception ex);
}
