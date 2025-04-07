package com.nd.cashflow.handlers;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.nd.cashflow.CryptoFragment;
import com.nd.cashflow.HomeFragment;
import com.nd.cashflow.R;
import com.nd.cashflow.StockFragment;

public class MenuEventsHandler implements View.OnClickListener {

    private FragmentTransaction transaction;
    private AppCompatActivity appCompatActivity;

    public MenuEventsHandler(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        this.transaction = this.appCompatActivity.getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        //Fragment current = this.appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.main_view_fragment);

        if (view.getId() == R.id.menu_home) {
            selectedFragment = new HomeFragment();
        } else if (view.getId() == R.id.menu_bitcoin) {
            selectedFragment = new CryptoFragment();
        } else if (view.getId() == R.id.menu_stock) {
            selectedFragment = new StockFragment();
        }

        if (selectedFragment != null) {
            this.transaction.replace(R.id.main_view_fragment, selectedFragment);
            this.transaction.addToBackStack(null);
            this.transaction.commit();
        }
    }
}
