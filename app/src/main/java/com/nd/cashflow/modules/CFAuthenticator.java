package com.nd.cashflow.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nd.cashflow.LoginActivity;
import com.nd.cashflow.MainActivity;

public class CFAuthenticator {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Activity activity;
    public CFAuthenticator(Activity _activity) {
        activity = _activity;
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                   if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                       Toast.makeText(null, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();

                        activity.startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();
                   } else {
                       Toast.makeText(null, "Sikertelen bejelentkezés! Hibás e-mail vagy jelszó", Toast.LENGTH_SHORT).show();
                   }
                });
    }

    public void registrateUser() {

    }

}
