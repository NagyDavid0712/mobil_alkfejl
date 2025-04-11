package com.nd.cashflow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nd.cashflow.R;
import com.nd.cashflow.model.User;
import com.nd.cashflow.modules.CFSession;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        TextView welcomeMessage = view.findViewById(R.id.welcome_message);

        CFSession session = CFSession.getInstance();
        User user = (User) session.getSessionObject();

        welcomeMessage.setText("Üdv, " + user.sName + "!");

        return view;
    }
}