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
import com.codelabs.pocketuni.adapters.EventsAdapter;
import com.codelabs.pocketuni.adapters.NoticesAdapter;
import com.codelabs.pocketuni.models.CalenderItem;
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

public class NoticesFragment extends Fragment {

    private ListView listView;
    private FirebaseFirestore db;
    private ArrayList<Notice> noticeList;
    private SharedPreferencesManager sharedPreferencesManager;
    private Calendar calendar;
    private CustomProgressDialog customProgressDialog;
    private TextView txtEmptyNotices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notices, container, false);

        listView = view.findViewById(R.id.lst_batchNotices);
        txtEmptyNotices = view.findViewById(R.id.txt_emptyBNotices);

        db = FirebaseFirestore.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        calendar = Calendar.getInstance();
        customProgressDialog = new CustomProgressDialog(getContext());

        init();

        return view;
    }

    private void init(){
        listView.setAdapter(null);
        noticeList = new ArrayList<>();

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
        Log.e(TAG, "filterNotices: " + date);

        customProgressDialog.createProgress();
        db.collection("Notices")
                .whereEqualTo("noticeDate", date)
                .whereEqualTo("noticeCourse", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentCourse())
                .whereEqualTo("noticeBatch", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatch())
                .whereEqualTo("noticeBatchType", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatchType())
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
                                noticeList.add(new Notice(snapsList.get("noticeBatch").toString(), snapsList.get("noticeBatchType").toString(), snapsList.get("noticeCourse").toString(),
                                        snapsList.get("noticeDate").toString(), snapsList.get("noticeDesc").toString(), snapsList.get("noticeTitle").toString()));
                            }

                            txtEmptyNotices.setEnabled(false);
                            txtEmptyNotices.setVisibility(View.GONE);
                            listView.setEnabled(true);
                            listView.setVisibility(View.VISIBLE);

                            if (getActivity()!=null){
                                NoticesAdapter listAdapter = new NoticesAdapter(getActivity(), noticeList);
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