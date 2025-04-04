package com.nd.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CurrencyFragment extends Fragment {

    //String[] items = new String[] { "1", "2", "3" };

    List<String> items = new ArrayList<>();

    public CurrencyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.currency_fragment, container, false);

        Spinner currencyList = view.findViewById(R.id.currency_list);

        items.add("Válassz valutát..."); items.add("EUR"); items.add("USD"); items.add("HUF");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        currencyList.setAdapter(adapter);

        return view;
    }
}