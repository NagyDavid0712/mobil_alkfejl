package com.nd.cashflow.handlers;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.nd.cashflow.CompanyDataFragment;
import com.nd.cashflow.R;
import com.nd.cashflow.model.Company;

public class OpenStockDescriptionPageEventHandler implements View.OnClickListener{

    private Company targetCompany;
    private AppCompatActivity appCompatActivity;

    public OpenStockDescriptionPageEventHandler(AppCompatActivity appCompatActivity, Company company) {
        this.appCompatActivity = appCompatActivity;
        this.targetCompany = company;
    }

    @Override
    public void onClick(View view) {
        Fragment dataFragment = new CompanyDataFragment(targetCompany);

        FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.main_view_fragment, dataFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
