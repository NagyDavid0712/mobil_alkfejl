package com.nd.cashflow.modules;

import com.nd.cashflow.model.Company;

import java.util.List;

public interface CFDataCompanyCallback {
    void onDataReady(List<Company> data);
    void onError(Exception ex);
}
