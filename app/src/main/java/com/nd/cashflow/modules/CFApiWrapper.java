package com.nd.cashflow.modules;

import com.nd.cashflow.model.Crypto;

import java.util.List;

import kotlin.NotImplementedError;

public class CFApiWrapper {

    private String COINGECKO_API_KEY = "CG-XkaYUd8MLTwDHdSsWShbQTUc";

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



}
