package com.nd.cashflow.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nd.cashflow.LoginActivity;
import com.nd.cashflow.MainActivity;
import com.nd.cashflow.model.User;

public class CFAuthenticator {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://mobil-alkfejl-db-default-rtdb.europe-west1.firebasedatabase.app");

    private Activity activity;
    public CFAuthenticator(Activity _activity) {
        activity = _activity;
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                   if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                       Toast.makeText(activity, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();

                        activity.startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();
                   } else {
                       Toast.makeText(activity, "Sikertelen bejelentkezés! Hibás e-mail vagy jelszó", Toast.LENGTH_SHORT).show();
                   }
                });
    }

    public void registrateUser(String fName, String sName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                   FirebaseUser user = mAuth.getCurrentUser();

                   if (user != null) {
                       DatabaseReference userRef = mDatabase.getReference("users").child(user.getUid());

                       User newUser = new User(fName, sName, email);

                       userRef.setValue(newUser).addOnCompleteListener(task1 -> {
                          if (task1.isSuccessful()) {
                              Toast.makeText(activity, "Sikeres regisztráció! Jelentkezz be!", Toast.LENGTH_SHORT).show();

                              activity.startActivity(new Intent(activity, LoginActivity.class));
                              activity.finish();
                          } else {
                              Toast.makeText(activity, "Hiba történt az adatok mentése közben!", Toast.LENGTH_SHORT).show();
                          }
                       });
                   }
                });
    }

}
