package com.nd.cashflow.handlers;

import android.util.Log;
import android.view.View;

public class OpenCryptoDescriptionPageEventHandler implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        Log.d("asd", (String) view.getTag());
    }
}
