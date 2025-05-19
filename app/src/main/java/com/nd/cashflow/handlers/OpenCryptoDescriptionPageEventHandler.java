package com.nd.cashflow.handlers;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.nd.cashflow.fragments.CryptoDataFragment;
import com.nd.cashflow.R;
import com.nd.cashflow.model.Crypto;

public class OpenCryptoDescriptionPageEventHandler implements View.OnClickListener{

    private Crypto targetCrypto;
    private AppCompatActivity appCompatActivity;

    public OpenCryptoDescriptionPageEventHandler(AppCompatActivity appCompatActivity, Crypto cypto) {
        this.targetCrypto = cypto;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    public void onClick(View view) {
        Fragment dataFragment = new CryptoDataFragment(targetCrypto);

        FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.main_view_fragment, dataFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
