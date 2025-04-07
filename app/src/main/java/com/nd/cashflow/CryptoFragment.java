package com.nd.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.nd.cashflow.components.CryptoCard;
import com.nd.cashflow.handlers.OpenCryptoDescriptionPageEventHandler;

public class CryptoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.crypto_fragment, container, false);

        GridLayout cryptoCardsContainer = view.findViewById(R.id.crypto_cards_container);

        for (int i = 0; i < 20; i++) {
            CryptoCard card = new CryptoCard(getContext(), "Test" + i, "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400");
            card.setOnClickListener(new OpenCryptoDescriptionPageEventHandler());
            cryptoCardsContainer.addView(card);

        }

        return view;
    }
}