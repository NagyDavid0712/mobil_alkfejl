package com.nd.cashflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nd.cashflow.modules.CFAuthenticator;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private ImageView switchToRegistrationView;

    private CFAuthenticator cfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        loginButton = findViewById(R.id.login_button);
        switchToRegistrationView = findViewById(R.id.switch_to_registration_view);

        cfa = new CFAuthenticator(this);

        switchToRegistrationView.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView email = findViewById(R.id.email_input);
                TextView password = findViewById(R.id.password_input);

                cfa.loginUser(email.getText().toString(), password.getText().toString());

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //finish();
            }
        });

    }

}
