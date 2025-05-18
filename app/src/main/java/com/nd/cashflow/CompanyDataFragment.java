package com.nd.cashflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.cashflow.model.Company;


public class CompanyDataFragment extends Fragment {

    private Company company;

    public CompanyDataFragment(Company company) {
        this.company = company;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company_data_fragment, container, false);

        TextView companyName = view.findViewById(R.id.company_name);
        TextView companyCountry = view.findViewById(R.id.company_country);
        TextView companyExchange = view.findViewById(R.id.company_exchange);
        TextView companyIndustry = view.findViewById(R.id.company_industry);
        TextView companyUrl = view.findViewById(R.id.company_url);

        companyName.setText(company.getName());
        companyCountry.setText(company.getCountry());
        companyExchange.setText(company.getExchange());
        companyIndustry.setText(company.getFinnhubIndustry());
        companyUrl.setText(company.getWeburl());

        return view;
    }
}