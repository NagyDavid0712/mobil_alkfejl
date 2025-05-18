package com.nd.cashflow.modules;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;


import androidx.annotation.NonNull;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nd.cashflow.MainActivity;
import com.nd.cashflow.model.Company;

import com.nd.cashflow.model.Crypto;
import com.nd.cashflow.model.SimpleExchange;
import com.nd.cashflow.utils.IsoCodes;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.util.AbstractMap.*;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


public class CFApiWrapper {

    private String COINGECKO_API_KEY = "CG-XkaYUd8MLTwDHdSsWShbQTUc";
    private String FINNHUB_API_KEY = "d0j196pr01ql09hpgqr0d0j196pr01ql09hpgqrg";
    private String EXCHANGERATE_API_KEY = "fe4efc054cf330f947659287";
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
    private OkHttpClient client;

    private CFApiWrapper() {
        client = new OkHttpClient();
    }


    public static CFApiWrapper getInstance() {
        if (instance == null) {
            instance = new CFApiWrapper();
        }

        return instance;
    }

    public void getCryptos(CFDataCallback callback) {
        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();

                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<Crypto>>() {
                    }.getType();

                    callback.onDataReceived(gson.fromJson(responseBody, listType));
                } else {
                    callback.onError(new Exception("Hiba"));
                }
            }
        });
    }

    public void getCompanys (CFDataCompanyCallback callback){
        ArrayList<Company> res = new ArrayList<>();
        int totalRequest = tickers.size();
        AtomicInteger completedRequest = new AtomicInteger(0);
        for (Map.Entry<String, String> t : tickers.entrySet()) {
            Request request = new Request.Builder()
                    .url("https://finnhub.io/api/v1/stock/profile2?symbol=" + t.getKey() + "&token=" + FINNHUB_API_KEY)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onError(e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        //Log.d("anyád", responseBody);
                        Gson gson = new Gson();

                        Company c = gson.fromJson(responseBody, Company.class);
                        //res.add(c);
                        synchronized (res) {
                            res.add(c);
                        }
                    }

                    int finished = completedRequest.incrementAndGet();
                    if (finished == totalRequest) {
                        callback.onDataReady(res);
                    }
                }
            });
        }

    }

    public void getExchangeData(CFDataExchangeCallback callback) {
        Locale locale = Locale.getDefault();
        String lang = locale.getLanguage();
        String country = locale.getCountry();
        Log.d("anyád", country);
        String iso = IsoCodes.ISO_CODES.get(lang + "_" + country).getIso();
        String name = IsoCodes.ISO_CODES.get(lang + "_" + country).getName();

        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", EXCHANGERATE_API_KEY, iso);

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();

                    JsonObject root = new JsonParser().parse(responseBody).getAsJsonObject();
                    JsonObject rates = root.getAsJsonObject("conversion_rates");

                    double eur = rates.get("EUR").getAsDouble();
                    double usd = rates.get("USD").getAsDouble();

                    callback.onDataready(new SimpleExchange(iso, name, eur, usd));
                } else {
                    callback.onError(new Exception("Hiba"));
                }
            }
        });
    }

}

