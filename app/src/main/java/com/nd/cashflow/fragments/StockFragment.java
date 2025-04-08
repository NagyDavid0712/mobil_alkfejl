package com.nd.cashflow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.nd.cashflow.R;
import com.nd.cashflow.components.CompanyCard;
import com.nd.cashflow.handlers.OpenStockDescriptionPageEventHandler;

public class StockFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment, container, false);

        GridLayout companyCardsContainer = view.findViewById(R.id.company_cards_container);

        for (int i = 0; i < 20; i++) {
            CompanyCard companyCard = new CompanyCard(getContext(), "Test " + i, "https://logo.clearbit.com/spacex.com");
            companyCard.setOnClickListener(new OpenStockDescriptionPageEventHandler());
            companyCardsContainer.addView(companyCard);
        }

        return view;
    }
}