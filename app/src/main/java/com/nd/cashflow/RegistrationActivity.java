package com.nd.cashflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nd.cashflow.modules.CFAuthenticator;

public class RegistrationActivity extends AppCompatActivity {

    private CFAuthenticator cfa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        Button registrationButton = findViewById(R.id.registration_button);
        ImageView switchToLoginView = findViewById(R.id.switch_to_login_view);

        cfa = new CFAuthenticator(this);

        switchToLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });


        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView fName = findViewById(R.id.reg_first_name_input);
                TextView sName = findViewById(R.id.reg_second_name_input);
                TextView email = findViewById(R.id.reg_email_input);
                TextView password = findViewById(R.id.reg_password_input);
                TextView password_again = findViewById(R.id.reg_password_again_input);

                cfa.registrateUser(fName.getText().toString(), sName.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });
    }
}
