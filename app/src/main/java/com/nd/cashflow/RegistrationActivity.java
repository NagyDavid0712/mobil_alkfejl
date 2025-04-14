package com.nd.cashflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nd.cashflow.components.InputFieldsErrorPopup;
import com.nd.cashflow.modules.CFAuthenticator;
import com.nd.cashflow.modules.CFFieldsChecker;

import org.w3c.dom.Text;

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
                //TextView fName = findViewById(R.id.reg_first_name_input);
                //TextView sName = findViewById(R.id.reg_second_name_input);
                //TextView email = findViewById(R.id.reg_email_input);
                //TextView password = findViewById(R.id.reg_password_input);
                //TextView password_again = findViewById(R.id.reg_password_again_input);

                String fName = ((TextView) findViewById(R.id.reg_first_name_input)).getText().toString().trim();
                String sName = ((TextView) findViewById(R.id.reg_second_name_input)).getText().toString().trim();
                String email = ((TextView) findViewById(R.id.reg_email_input)).getText().toString().trim();
                String password = ((TextView) findViewById(R.id.reg_password_input)).getText().toString().trim();
                String password_again = ((TextView) findViewById(R.id.reg_password_again_input)).getText().toString().trim();

                String fNameError = CFFieldsChecker.checkBaseField(fName);
                String sNameError = CFFieldsChecker.checkBaseField(sName);
                String emailError = CFFieldsChecker.checkEmailField(email);
                String passwordError = CFFieldsChecker.checkPasswordField(password);
                String passwordMatchError = null;

                if (!password.equals(password_again)) passwordMatchError = "A két jelszónak meg kell egyeznie!";

                if (fNameError != null || sNameError != null || emailError != null || passwordError != null || passwordMatchError != null) {
                    InputFieldsErrorPopup.showPopup(view, new String[] { fNameError, sNameError, emailError, passwordError, passwordMatchError });
                } else {
                    cfa.registrateUser(fName, sName, email, password);
                }
            }
        });
    }
}
