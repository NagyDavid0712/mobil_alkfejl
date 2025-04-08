package com.nd.cashflow;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nd.cashflow.fragments.HomeFragment;
import com.nd.cashflow.handlers.MenuEventsHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        setContentView(R.layout.main_layout);

        ImageView menuItems[] = new ImageView[] {
                findViewById(R.id.menu_home),
                findViewById(R.id.menu_currency),
                findViewById(R.id.menu_bitcoin),
                findViewById(R.id.menu_stock),
                findViewById(R.id.menu_options),
                findViewById(R.id.menu_profile)
        };

        MenuEventsHandler menuEventsHandler = new MenuEventsHandler(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_view_fragment, new HomeFragment()).commit();

        for (var e: menuItems) {
            e.setOnClickListener(menuEventsHandler);
        }
    }
}