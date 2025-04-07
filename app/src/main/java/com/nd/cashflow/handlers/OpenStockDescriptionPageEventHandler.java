package com.nd.cashflow.handlers;

import android.util.Log;
import android.view.View;

public class OpenStockDescriptionPageEventHandler implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Log.d("asd", (String) view.getTag());
    }
}
