package com.nd.cashflow.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.nd.cashflow.R;
import com.nd.cashflow.components.CryptoCard;
import com.nd.cashflow.handlers.OpenCryptoDescriptionPageEventHandler;
import com.nd.cashflow.model.Crypto;
import com.nd.cashflow.modules.CFApiWrapper;
import com.nd.cashflow.modules.CFDataCallback;

import java.util.List;

public class CryptoFragment extends Fragment {

    private AppCompatActivity appCompatActivity;

    public CryptoFragment(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CFApiWrapper Apiinstance = CFApiWrapper.getInstance();
        View view = inflater.inflate(R.layout.crypto_fragment, container, false);

        GridLayout cryptoCardsContainer = view.findViewById(R.id.crypto_cards_container);


        Apiinstance.getCryptos(new CFDataCallback() {
            @Override
            public void onDataReceived(List<Crypto> data) {
                getActivity().runOnUiThread(() -> {
                    data.forEach(x -> {
                        CryptoCard card = new CryptoCard(getContext(), x.getName(), x.getImage());
                        card.setOnClickListener(new OpenCryptoDescriptionPageEventHandler(appCompatActivity, x));
                        cryptoCardsContainer.addView(card);
                    });
                });
            }

            @Override
            public void onError(Exception ex) {

            }
        });

        return view;
    }
}