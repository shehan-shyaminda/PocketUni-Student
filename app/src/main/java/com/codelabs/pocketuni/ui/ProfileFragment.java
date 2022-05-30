package com.codelabs.pocketuni.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.codelabs.pocketuni.LoginActivity;
import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.SignUpActivity;
import com.codelabs.pocketuni.services.SharedPreferencesManager;
import com.codelabs.pocketuni.utils.CustomProgressDialog;

public class ProfileFragment extends Fragment {

    private ImageView btnProfile, btnEditProfile;
    private Button btnLogout;
    private TextView txtStudentEmail, txtStudentName;
    private ConstraintLayout layoutProfile, layoutEditProfile;
    private SharedPreferencesManager sharedPreferencesManager;
    private CustomProgressDialog customProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        btnProfile = view.findViewById(R.id.btn_profile);
        btnEditProfile = view.findViewById(R.id.btn_editProfile);
        layoutProfile = view.findViewById(R.id.layout_profile);
        layoutEditProfile = view.findViewById(R.id.layout_editProfile);
        txtStudentEmail = view.findViewById(R.id.txt_profileEmail);
        txtStudentName = view.findViewById(R.id.txt_profileName);
        btnLogout = view.findViewById(R.id.btn_logout);

        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        customProgressDialog = new CustomProgressDialog(getContext());

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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customProgressDialog.createProgress();

                sharedPreferencesManager.clearPreferences(SharedPreferencesManager.USER_ID);
                sharedPreferencesManager.clearPreferences(SharedPreferencesManager.USER_DETAILS);
                sharedPreferencesManager.savePreferences(SharedPreferencesManager.USER_LOGGED_IN, false);

                customProgressDialog.dismissProgress();

                startActivity(new Intent(getContext(), LoginActivity.class));
                Animatoo.animateSlideRight(getContext());
                getActivity().finish();
            }
        });

        return view;
    }

    private void init() {
        layoutEditProfile.setVisibility(View.GONE);
        layoutEditProfile.setEnabled(false);
        layoutProfile.setVisibility(View.VISIBLE);
        layoutProfile.setEnabled(true);

        txtStudentEmail.setText(sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentEmail());
        if (sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentName().isEmpty()){
            txtStudentName.setText(sharedPreferencesManager.getPreferences(SharedPreferencesManager.USER_ID));
        }else{
            txtStudentName.setText(sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentEmail().split("@")[0].toUpperCase());
//            txtStudentName.setText(sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentName());
        }
    }
}