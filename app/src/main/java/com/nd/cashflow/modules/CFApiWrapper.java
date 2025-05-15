package com.nd.cashflow.modules;

import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;

import com.nd.cashflow.model.Company;
import com.nd.cashflow.model.Crypto;

import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;
import okhttp3.Request;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.util.AbstractMap.*;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class CFApiWrapper {

    private String COINGECKO_API_KEY = "CG-XkaYUd8MLTwDHdSsWShbQTUc";
    private String FINNHUB_API_KEY = "d0j196pr01ql09hpgqr0d0j196pr01ql09hpgqrg";
    private Map<String, String> tickers = Map.ofEntries(
            new SimpleEntry<>("AAPL", "apple.com"),
            new SimpleEntry<>("MSFT", "microsoft.com"),
            new SimpleEntry<>("2222.SR", "aramco.com"),
            new SimpleEntry<>("GOOGL", "abc.xyz"),
            new SimpleEntry<>("AMZN", "amazon.com"),
            new SimpleEntry<>("NVDA", "nvidia.com"),
            new SimpleEntry<>("BRK-B", "berkshirehathaway.com"),
            new SimpleEntry<>("META", "meta.com"),
            new SimpleEntry<>("TSLA", "tesla.com"),
            new SimpleEntry<>("TSM", "tsmc.com"),
            new SimpleEntry<>("LLY", "lilly.com"),
            new SimpleEntry<>("JNJ", "jnj.com"),
            new SimpleEntry<>("JPM", "jpmorganchase.com"),
            new SimpleEntry<>("XOM", "corporate.exxonmobil.com"),
            new SimpleEntry<>("V", "visa.com"),
            new SimpleEntry<>("WMT", "walmart.com"),
            new SimpleEntry<>("005930.KS", "samsung.com"),
            new SimpleEntry<>("NESN.SW", "nestle.com"),
            new SimpleEntry<>("0700.HK", "tencent.com"),
            new SimpleEntry<>("MC.PA", "lvmh.com")
    );

    private static CFApiWrapper instance;

    private CFApiWrapper() {  }

    public static CFApiWrapper getInstance() {
        if (instance == null) {
            instance = new CFApiWrapper();
        }

        return instance;
    }

    public List<Crypto> getCryptos() {
        return null;
    }

    public void getCompanys(CFDataCompanyCallback callback) {
        for (Map.Entry<String, String> t : tickers.entrySet()) {
            Request request = new Request.Builder()
                    .url("https://finnhub.io/api/v1/stock/profile2?symbol=" + t.getKey() + "&token=" + FINNHUB_API_KEY)
                    .build();


        }
    }

}
