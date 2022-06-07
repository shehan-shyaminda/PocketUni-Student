package com.codelabs.pocketuni.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.adapters.AnnouncementAdapter;
import com.codelabs.pocketuni.adapters.EventsAdapter;
import com.codelabs.pocketuni.adapters.NoticesAdapter;
import com.codelabs.pocketuni.models.Announcement;
import com.codelabs.pocketuni.models.Notice;
import com.codelabs.pocketuni.services.SharedPreferencesManager;
import com.codelabs.pocketuni.utils.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AnnouncementsFragment extends Fragment {

    private ListView listView;
    private FirebaseFirestore db;
    private ArrayList<Announcement> announcementList;
    private SharedPreferencesManager sharedPreferencesManager;
    private Calendar calendar;
    private CustomProgressDialog customProgressDialog;
    private TextView txtEmptyNotices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_announcements, container, false);

        listView = view.findViewById(R.id.lst_publicNotices);
        txtEmptyNotices = view.findViewById(R.id.txt_emptyPNotices);

        db = FirebaseFirestore.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        calendar = Calendar.getInstance();
        customProgressDialog = new CustomProgressDialog(getContext());

        init();

        return view;
    }

    private void init(){
        listView.setAdapter(null);
        announcementList = new ArrayList<>();

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
        Log.e(TAG, "filterNotification: " + date);

        customProgressDialog.createProgress();
        db.collection("Notifications")
                .whereEqualTo("notificationDate", date)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            Log.e(TAG, "onSuccess: Empty Collection");
                            listView.setEnabled(false);
                            listView.setVisibility(View.GONE);
                            txtEmptyNotices.setEnabled(true);
                            txtEmptyNotices.setVisibility(View.VISIBLE);
                        }else{
                            DocumentSnapshot snapsList;
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                snapsList = task.getResult().getDocuments().get(i);
                                announcementList.add(new Announcement(snapsList.get("notificationDate").toString(), snapsList.get("notificationDesc").toString(), snapsList.get("notificationTitle").toString()));
                            }

                            txtEmptyNotices.setEnabled(false);
                            txtEmptyNotices.setVisibility(View.GONE);
                            listView.setEnabled(true);
                            listView.setVisibility(View.VISIBLE);

                            if (getActivity()!=null){
                                AnnouncementAdapter listAdapter = new AnnouncementAdapter(getActivity(), announcementList);
                                listView.setAdapter(listAdapter);
                            }
                        }
                        customProgressDialog.dismissProgress();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        customProgressDialog.dismissProgress();
                        Log.e(TAG, "onFailure: " + e);
                    }
                });
    }
}