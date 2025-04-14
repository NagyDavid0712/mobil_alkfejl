package com.nd.cashflow.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nd.cashflow.R;

public class InputFieldsErrorPopup {

    public static void showPopup(View view, String[] errors) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = inflater.inflate(R.layout.input_fields_error_layout, null);

        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;

        boolean focusable = false;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height,focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        LinearLayout errorsListContainer = popupView.findViewById(R.id.errors_list_container);

        for (int i = 0; i < errors.length; i++) {
            TextView err = new TextView(popupView.getContext());
            err.setText(errors[i]);
            err.setTextColor(Color.WHITE);
            errorsListContainer.addView(err);
        }

        Button closeButton = popupView.findViewById(R.id.popup_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

}
