package com.nd.cashflow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


        return view;
    }
}