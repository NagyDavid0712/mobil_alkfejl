package com.nd.cashflow.components;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.nd.cashflow.R;

public class CompanyCard extends FrameLayout {

    private ImageView companyImage;
    private TextView companyName;

    public CompanyCard(Context context, String cmpName, String cmpImageUrl) {
        super(context);

        companyImage = new ImageView(context);
        companyName = new TextView(context);

        float scale = context.getResources().getDisplayMetrics().density;

        FrameLayout.LayoutParams sizeParams = new FrameLayout.LayoutParams((int)(150 * scale + 0.5f), (int) (150 * scale + 0.5f));
        sizeParams.setMargins(10, 10, 10, 10);
        setLayoutParams(sizeParams);
        setBackgroundResource(R.drawable.rounded_dashboard_panel);

        setTag(cmpName);

        FrameLayout.LayoutParams imageSizeParams = new FrameLayout.LayoutParams((int) (90 * scale + 0.5f), (int) (90 * scale + 0.5f));
        //imageSizeParams.width = (int) (70 * scale + 0.5f);
        //imageSizeParams.height = (int) (70 * scale + 0.5f);
        imageSizeParams.gravity = Gravity.CENTER;
        companyImage.setLayoutParams(imageSizeParams);
        Glide.with(context)
                .load(cmpImageUrl)
                .into(companyImage);

        FrameLayout.LayoutParams textSizeParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER | Gravity.BOTTOM);
        //textSizeParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        textSizeParams.bottomMargin = (int) (10 * scale + 0.5f);
        companyName.setLayoutParams(textSizeParams);
        companyName.setText(cmpName);
        companyName.setTextColor(Color.WHITE);

        addView(companyImage);
        addView(companyName);
    }
}
