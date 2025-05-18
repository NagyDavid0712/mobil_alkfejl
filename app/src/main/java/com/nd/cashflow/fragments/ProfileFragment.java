package com.nd.cashflow.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nd.cashflow.LoginActivity;
import com.nd.cashflow.MainActivity;
import com.nd.cashflow.R;
import com.nd.cashflow.model.User;
import com.nd.cashflow.modules.CFSession;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        TextView userFirstname = view.findViewById(R.id.user_first_name);
        TextView userSecondname = view.findViewById(R.id.user_second_name);
        TextView userEmail = view.findViewById(R.id.user_email_address);

        CFSession session = CFSession.getInstance();
        User user = (User) session.getSessionObject();

        userFirstname.setText(user.fName);
        userSecondname.setText(user.sName);
        userEmail.setText(user.email);


        Button changePasswordButton = view.findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add meg a jelszavakat");

                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(50, 40, 50, 10); // Opcionális: belső margók

                EditText oldPassword = new EditText(getContext());
                oldPassword.setHint("Régi jelszó");
                oldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(oldPassword);

                EditText newPasswordInput = new EditText(getContext());
                newPasswordInput.setHint("Új jelszó");
                newPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                layout.addView(newPasswordInput);

                builder.setView(layout);

                builder.setPositiveButton("Ok", (dialog, wich) -> {
                    String npassword = newPasswordInput.getText().toString();
                    String opassword = oldPassword.getText().toString();

                    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();

                    AuthCredential credential = EmailAuthProvider.getCredential(user.email, opassword);

                    fuser.reauthenticate(credential)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {

                                    fuser.updatePassword(npassword)
                                            .addOnCompleteListener(updateTask -> {
                                               if (updateTask.isSuccessful()) {
                                                   Toast.makeText(getContext(), "Jelszó frissítve", Toast.LENGTH_SHORT).show();
                                               } else {
                                                   Toast.makeText(getContext(), "Hiba: " + updateTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                            });
                                } else {
                                    Toast.makeText(getContext(), "Hibás jelszó!", Toast.LENGTH_SHORT).show();
                                }
                            });
                });

                builder.setNegativeButton("Mégse", (dialog, wich) -> dialog.cancel());
                builder.show();
            }
        });

        Button deleteProfileButton = view.findViewById(R.id.delete_profile_button);
        deleteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Biztos törölni szeretnéd a felhasználót?");

                builder.setPositiveButton("Igen", (dialog, wich) -> {
                    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase db = FirebaseDatabase.getInstance("https://mobil-alkfejl-db-default-rtdb.europe-west1.firebasedatabase.app");
                    DatabaseReference ref = db.getReference();
                    if (fuser != null) {

                        String uid = fuser.getUid();

                        ref.child("users").child(uid).removeValue()
                                .addOnCompleteListener(task -> {
                                   if (task.isSuccessful()) {

                                       fuser.delete()
                                               .addOnCompleteListener(delTask -> {
                                                  if (delTask.isSuccessful()) {
                                                      FirebaseAuth.getInstance().signOut();
                                                      Intent intent = new Intent(getActivity(), LoginActivity.class);
                                                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                      startActivity(intent);
                                                      requireActivity().finish();
                                                      Toast.makeText(getContext(), "Felhasználó törölve", Toast.LENGTH_SHORT).show();
                                                  } else {
                                                      Toast.makeText(getContext(), "Hiba törlés közben", Toast.LENGTH_SHORT).show();
                                                  }
                                               });
                                   } else {
                                       Toast.makeText(getContext(), "Hiba törlés közben", Toast.LENGTH_SHORT).show();
                                   }
                                });
                    } else {
                        Toast.makeText(getContext(), "Valami baj van", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Nem", (dialog, wich) -> dialog.cancel());
                builder.show();
            }
        });

        return view;
    }
}