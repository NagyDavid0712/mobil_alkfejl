package com.nd.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.cashflow.model.Crypto;


public class CryptoDataFragment extends Fragment {

    private Crypto crypto;

    public CryptoDataFragment(Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crypto_data_fragment, container, false);

        TextView cryptoNameText = view.findViewById(R.id.crypto_name_text);
        TextView currentPrice = view.findViewById(R.id.current_price);
        TextView highText24H = view.findViewById(R.id.high_text_24h);
        TextView lowText24H = view.findViewById(R.id.low_text_24);
        TextView lastUpdate = view.findViewById(R.id.last_update);

        cryptoNameText.setText(crypto.getName());
        currentPrice.setText(crypto.getCurrent_price() + "");
        highText24H.setText(crypto.getHigh_24h() + "");
        lowText24H.setText(crypto.getLow_24h() + "");
        lastUpdate.setText(crypto.getLast_updated());

        return view;
    }
}