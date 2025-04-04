package com.nd.cashflow;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

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

        chart.invalidate();

        return view;
    }
}