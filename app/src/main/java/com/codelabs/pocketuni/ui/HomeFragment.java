package com.codelabs.pocketuni.ui;

import android.os.Bundle;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.applandeo.materialcalendarview.CalendarView;
import com.codelabs.pocketuni.MainActivity;
import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.adapters.EventsAdapter;
import com.codelabs.pocketuni.models.CalenderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageView btnCalender;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;
    private ListView listView;
    private CalendarView calendarView;
    private ConstraintLayout bottomSheetContainer;
    private FirebaseFirestore db;
    private CalenderItem calenderItem;
    private ArrayList<CalenderItem> eventsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnCalender = view.findViewById(R.id.btn_calender);

        bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottSheetDialogTheme);
        bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_calender, view.findViewById(R.id.bottom_sheet_container));
        bottomSheetContainer = bottomSheetView.findViewById(R.id.bottom_sheet_container);
        calendarView = bottomSheetView.findViewById(R.id.calendarView);
        listView = view.findViewById(R.id.lst_calenderEvents);

        db = FirebaseFirestore.getInstance();

        init();

        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO : ADD Event

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e(TAG, "onStateChanged: Hidden Sheet");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e(TAG, "onStateChanged: Close Sheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e(TAG, "onStateChanged: Expand Sheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e(TAG, "onStateChanged: Dragging Sheet");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e(TAG, "onStateChanged: Settling Sheet");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return view;
    }

    private void init() {
        eventsList = new ArrayList<>();

        // get data from calender collection
        db.collection("Calander").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if (queryDocumentSnapshots.isEmpty()){
//                            Log.e(TAG, "onSuccess: Empty Collection");
//                        }else{
//                            DocumentSnapshot snapsList;
//                            for(int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++){
//                                snapsList = queryDocumentSnapshots.getDocuments().get(i);
//                                eventsList.add(new CalenderItem(snapsList.get("calenderCourse").toString(), snapsList.get("calenderBatch").toString(), snapsList.get("calenderBatchType").toString(),
//                                        snapsList.get("calenderDate").toString(), snapsList.get("calenderDescription").toString(), snapsList.get("calenderTime").toString(),
//                                        snapsList.get("calenderTitle").toString()));
//                                Log.e(TAG, "onSuccess: " + queryDocumentSnapshots.getDocuments() );
//                            }
//                        }
//                    }
//                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            Log.e(TAG, "onSuccess: Empty Collection");
                            setAdapter();
                        }else{
                            DocumentSnapshot snapsList;
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                snapsList = task.getResult().getDocuments().get(i);
                                eventsList.add(new CalenderItem(snapsList.get("calenderCourse").toString(), snapsList.get("calenderBatch").toString(), snapsList.get("calenderBatchType").toString(),
                                        snapsList.get("calenderDate").toString(), snapsList.get("calenderDescription").toString(), snapsList.get("calenderTime").toString(),
                                        snapsList.get("calenderTitle").toString()));
//                                Log.e(TAG, "onSuccess: " + task.getResult().getDocuments() );
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: " + e);
                    }
                });
    }

    public void setAdapter(){
        // get data from calender collection
        db.collection("HallAllocation").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if (queryDocumentSnapshots.isEmpty()){
//                            Log.e(TAG, "onSuccess: Empty Collection");
//                            EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
//                            listView.setAdapter(listAdapter);
//                            Log.e(TAG, "init: " + eventsList );
//                        }else{
//                            DocumentSnapshot snapsList;
//                            for(int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++){
//                                snapsList = queryDocumentSnapshots.getDocuments().get(i);
//                                eventsList.add(new CalenderItem(snapsList.get("allocateDate").toString(), snapsList.get("allocatedTime").toString(), snapsList.get("allocationType").toString(),
//                                        snapsList.get("batch").toString(), snapsList.get("batchType").toString(), snapsList.get("course").toString(), snapsList.get("hallName").toString(),
//                                        snapsList.get("lecturer").toString(), snapsList.get("subject").toString()));
//                                Log.e(TAG, "onSuccess: " + queryDocumentSnapshots.getDocuments() );
//                            }
//                            EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
//                            listView.setAdapter(listAdapter);
//                        }
//                    }
//                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            Log.e(TAG, "onSuccess: Empty Collection");
                            EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
                            listView.setAdapter(listAdapter);
                        }else{
                            DocumentSnapshot snapsList;
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                snapsList = task.getResult().getDocuments().get(i);
                                eventsList.add(new CalenderItem(snapsList.get("allocateDate").toString(), snapsList.get("allocatedTime").toString(), snapsList.get("allocationType").toString(),
                                        snapsList.get("batch").toString(), snapsList.get("batchType").toString(), snapsList.get("course").toString(), snapsList.get("hallName").toString(),
                                        snapsList.get("lecturer").toString(), snapsList.get("subject").toString()));
//                                Log.e(TAG, "onSuccess: " + task.getResult().getDocuments() );
                            }
                            EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
                            listView.setAdapter(listAdapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: " + e);
                    }
                });
    }
}