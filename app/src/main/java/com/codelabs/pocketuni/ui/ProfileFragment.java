package com.codelabs.pocketuni.ui;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codelabs.pocketuni.R;

public class ProfileFragment extends Fragment {

    ImageView btnProfile, btnEditProfile;
    ConstraintLayout layoutProfile, layoutEditProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnProfile = view.findViewById(R.id.btn_profile);
        btnEditProfile = view.findViewById(R.id.btn_editProfile);
        layoutProfile = view.findViewById(R.id.layout_profile);
        layoutEditProfile = view.findViewById(R.id.layout_editProfile);

        init();

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutProfile.setVisibility(View.GONE);
                layoutProfile.setEnabled(false);
                layoutEditProfile.setVisibility(View.VISIBLE);
                layoutEditProfile.setEnabled(true);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutEditProfile.setVisibility(View.GONE);
                layoutEditProfile.setEnabled(false);
                layoutProfile.setVisibility(View.VISIBLE);
                layoutProfile.setEnabled(true);
            }
        });
        return view;
    }

    private void init() {
        layoutEditProfile.setVisibility(View.GONE);
        layoutEditProfile.setEnabled(false);
        layoutProfile.setVisibility(View.VISIBLE);
        layoutProfile.setEnabled(true);
    }
}