package com.nd.cashflow.modules;

import com.nd.cashflow.model.Crypto;

import java.util.List;

public interface CFDataCallback {
    void onDataReceived(List<Crypto> data);
    void onError(Exception ex);
}
