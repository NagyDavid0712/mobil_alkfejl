package com.nd.cashflow.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.text.Layout;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nd.cashflow.R;

public class CryptoCard extends FrameLayout{

    //private FrameLayout cardContainer;
    private ImageView cryptoImage;
    private TextView cryptoName;

    public CryptoCard(Context context, String crpName, String crpImageUrl) {
        super(context);
        //cardContainer = new FrameLayout(context);
        cryptoImage = new ImageView(context);
        cryptoName = new TextView(context);

        float scale = context.getResources().getDisplayMetrics().density;

        FrameLayout.LayoutParams sizeParams = new FrameLayout.LayoutParams((int)(150 * scale + 0.5f), (int) (150 * scale + 0.5f));
        sizeParams.setMargins(10, 10, 10, 10);
        setLayoutParams(sizeParams);
        setBackgroundResource(R.drawable.rounded_dashboard_panel);

        setTag(crpName);

        FrameLayout.LayoutParams imageSizeParams = new FrameLayout.LayoutParams((int) (90 * scale + 0.5f), (int) (90 * scale + 0.5f));
        //imageSizeParams.width = (int) (70 * scale + 0.5f);
        //imageSizeParams.height = (int) (70 * scale + 0.5f);
        imageSizeParams.gravity = Gravity.CENTER;
        cryptoImage.setLayoutParams(imageSizeParams);
        Glide.with(context)
                .load(crpImageUrl)
                .into(cryptoImage);

        FrameLayout.LayoutParams textSizeParams = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER | Gravity.BOTTOM);
        //textSizeParams.gravity = Gravity.CENTER | Gravity.BOTTOM;
        textSizeParams.bottomMargin = (int) (10 * scale + 0.5f);
        cryptoName.setLayoutParams(textSizeParams);
        cryptoName.setText(crpName);
        cryptoName.setTextColor(Color.WHITE);

        addView(cryptoImage);
        addView(cryptoName);
    }

}
