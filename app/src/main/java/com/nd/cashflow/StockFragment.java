package com.nd.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;

import com.nd.cashflow.components.CompanyCard;

public class StockFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment, container, false);

        GridLayout companyCardsContainer = view.findViewById(R.id.company_cards_container);

        for (int i = 0; i < 20; i++) {
            companyCardsContainer.addView(new CompanyCard(getContext(), "Test " + i, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQP7dkw9575sioFCg1zePcM4JSg98imdw582Q&s"));
        }

        return view;
    }
}