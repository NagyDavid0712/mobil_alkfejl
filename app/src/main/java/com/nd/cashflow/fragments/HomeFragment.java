package com.nd.cashflow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.cashflow.R;
import com.nd.cashflow.model.SimpleExchange;
import com.nd.cashflow.model.User;
import com.nd.cashflow.modules.CFApiWrapper;
import com.nd.cashflow.modules.CFDataExchangeCallback;
import com.nd.cashflow.modules.CFSession;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        TextView welcomeMessage = view.findViewById(R.id.welcome_message);
        TextView exchangeName = view.findViewById(R.id.exchange_name);
        TextView eurToCurrency = view.findViewById(R.id.eur_to_currency);
        TextView usdToCurrency = view.findViewById(R.id.usd_to_currency);

        CFSession session = CFSession.getInstance();
        User user = (User) session.getSessionObject();
        CFApiWrapper Apiinstance = CFApiWrapper.getInstance();

        welcomeMessage.setText("Üdv, " + user.sName + "!");

        Apiinstance.getExchangeData(new CFDataExchangeCallback() {
            @Override
            public void onDataready(SimpleExchange data) {
                getActivity().runOnUiThread(() -> {
                    exchangeName.setText(String.format("%s (%s)", data.getName(), data.getIso()));
                    eurToCurrency.setText(String.format("%f EUR", data.getEurPrice()));
                    usdToCurrency.setText(String.format("%f USD", data.getUsdPrice()));
                });
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        });


        return view;
    }
}