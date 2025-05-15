package com.nd.cashflow.modules;

import androidx.annotation.NonNull;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nd.cashflow.model.Crypto;

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

public class CFApiWrapper {

    private String COINGECKO_API_KEY = "CG-XkaYUd8MLTwDHdSsWShbQTUc";

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
                    Type listType = new TypeToken<ArrayList<Crypto>>() {}.getType();

                    callback.onDataReceived(gson.fromJson(responseBody, listType));
                } else {
                    callback.onError(new Exception("Hiba"));
                }
            }
        });

    }

}
