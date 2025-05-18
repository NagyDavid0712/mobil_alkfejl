package com.nd.cashflow.handlers;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.nd.cashflow.fragments.CurrencyFragment;
import com.nd.cashflow.fragments.CryptoFragment;
import com.nd.cashflow.fragments.HomeFragment;
import com.nd.cashflow.fragments.OptionsFragment;
import com.nd.cashflow.fragments.ProfileFragment;
import com.nd.cashflow.R;
import com.nd.cashflow.fragments.StockFragment;

import java.util.HashMap;
import java.util.Map;

public class MenuEventsHandler implements View.OnClickListener {

    private AppCompatActivity appCompatActivity;

    private Map<Integer, Fragment> menuItems;

    public MenuEventsHandler(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
        menuItems = new HashMap<Integer, Fragment>();
        menuItems.put(R.id.menu_home, new HomeFragment());
        menuItems.put(R.id.menu_currency, new CurrencyFragment());
        menuItems.put(R.id.menu_bitcoin, new CryptoFragment(appCompatActivity));
        menuItems.put(R.id.menu_stock, new StockFragment(appCompatActivity));
        menuItems.put(R.id.menu_options, new OptionsFragment());
        menuItems.put(R.id.menu_profile, new ProfileFragment());
    }

    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        //Fragment current = this.appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.main_view_fragment);
        FragmentTransaction transaction = this.appCompatActivity.getSupportFragmentManager().beginTransaction();
        /*if (view.getId() == R.id.menu_home) {
            selectedFragment = new HomeFragment();
        } else if (view.getId() == R.id.menu_currency) {
            selectedFragment = new CurrencyFragment();
        } else if (view.getId() == R.id.menu_bitcoin) {
            selectedFragment = new CryptoFragment();
        } else if (view.getId() == R.id.menu_stock) {
            selectedFragment = new StockFragment();
        } else if (view.getId() == R.id.menu_options) {
            selectedFragment = new OptionsFragment();
        } else if (view.getId() == R.id.menu_profile) {
            selectedFragment = new ProfileFragment();
        }*/

        selectedFragment = menuItems.get(view.getId());
        
        if (selectedFragment != null) {
            transaction.replace(R.id.main_view_fragment, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
