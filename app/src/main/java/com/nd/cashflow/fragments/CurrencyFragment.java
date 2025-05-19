package com.nd.cashflow.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.nd.cashflow.R;
import com.nd.cashflow.model.CurrencyExchange;
import com.nd.cashflow.modules.CFApiWrapper;
import com.nd.cashflow.modules.CFDataCurrencyExchangeCallback;

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

        FrameLayout currencyInformationContainer = view.findViewById(R.id.currency_information_container);
        TextView eurToCR = view.findViewById(R.id.eur_to_cr);
        TextView usdToCR = view.findViewById(R.id.usd_to_cr);
        TextView gbpToCR = view.findViewById(R.id.gbp_to_cr);

        currencyList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) adapterView.getItemAtPosition(i);

                Log.d("anyád", selected);


                if(i != 0) {
                    CFApiWrapper.getInstance().getCurrencyExchange(selected, new CFDataCurrencyExchangeCallback() {
                        @Override
                        public void onDataReady(CurrencyExchange data) {
                            getActivity().runOnUiThread(() -> {
                                eurToCR.setText(String.format("1 EUR = %.2f %s", data.getEur(), selected));
                                usdToCR.setText(String.format("1 USD = %.2f %s", data.getUsd(), selected));
                                gbpToCR.setText(String.format("1 GBP = %.2f %s", data.getGbp(), selected));
                            });
                        }

                        @Override
                        public void onError(Exception ex) {
                            ex.printStackTrace();
                        }
                    });

                    currencyInformationContainer.setVisibility(View.VISIBLE);
                } else {
                    currencyInformationContainer.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*
        BarChart chart = view.findViewById(R.id.inflation_chart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, 10f));
        entries.add(new BarEntry(2f, 20f));
        entries.add(new BarEntry(3f, 15f));
        entries.add(new BarEntry(4f, 30f));

        BarDataSet dataSet = new BarDataSet(entries, "Eladások");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14f);

        BarData barData = new BarData(dataSet);
        chart.setData(barData);

        chart.getDescription().setEnabled(false);
        chart.setFitBars(true);
        chart.animateY(1000);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        chart.invalidate();*/

        return view;
    }
}