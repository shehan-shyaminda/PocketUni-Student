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
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.codelabs.pocketuni.LoginActivity;
import com.codelabs.pocketuni.MainActivity;
import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.adapters.EventsAdapter;
import com.codelabs.pocketuni.models.CalenderItem;
import com.codelabs.pocketuni.services.SharedPreferencesManager;
import com.codelabs.pocketuni.utils.CustomProgressDialog;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private ImageView btnCalender;
    private TextView txtEmptyEvents, txtWelcomeName;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;
    private ListView listView;
    private CalendarView calendarView;
    private Calendar calendar;
    private ConstraintLayout bottomSheetContainer;
    private FirebaseFirestore db;
    private CalenderItem calenderItem;
    private ArrayList<CalenderItem> eventsList;
    private List<EventDay> events;
    private SharedPreferencesManager sharedPreferencesManager;
    private CustomProgressDialog customProgressDialog;

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
        txtEmptyEvents = view.findViewById(R.id.txt_emptyEvents);
        txtWelcomeName = view.findViewById(R.id.txt_welcome_name);

        db = FirebaseFirestore.getInstance();
        calendar = Calendar.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        customProgressDialog = new CustomProgressDialog(getContext());

        init();

        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : ADD Event

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                filterEvents(clickedDayCalendar);
                bottomSheetDialog.cancel();
            }
        });

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContainer);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
//                        Log.e(TAG, "onStateChanged: Hidden Sheet");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
//                        Log.e(TAG, "onStateChanged: Close Sheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        Log.e(TAG, "onStateChanged: Expand Sheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
//                        Log.e(TAG, "onStateChanged: Dragging Sheet");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
//                        Log.e(TAG, "onStateChanged: Settling Sheet");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return view;
    }

    private void init(){
//        customProgressDialog.createProgress();

        if (sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentName().isEmpty()){
            txtWelcomeName.setText(sharedPreferencesManager.getPreferences(SharedPreferencesManager.USER_ID));
        }else{
            txtWelcomeName.setText(sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentName());
        }
        getCalenderEvents();
        filterEvents(calendar);

//        customProgressDialog.dismissProgress();
    }

    private void filterEvents(Calendar calendar){
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
        Log.e(TAG, "filterEvents: " + date);

        listView.setAdapter(null);
        eventsList = new ArrayList<>();

        customProgressDialog.createProgress();
//         get data from calender collection
        db.collection("Events")
                .whereEqualTo("eventDate", date)
                .whereEqualTo("eventCourse", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentCourse())
                .whereEqualTo("eventBatch", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatch())
                .whereEqualTo("eventBatchType", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatchType())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            Log.e(TAG, "onSuccess: Empty Collection");
                            listView.setEnabled(false);
                            listView.setVisibility(View.GONE);
                            txtEmptyEvents.setEnabled(true);
                            txtEmptyEvents.setVisibility(View.VISIBLE);
                        }else{
                            DocumentSnapshot snapsList;
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                snapsList = task.getResult().getDocuments().get(i);
                                eventsList.add(new CalenderItem(snapsList.get("eventBatch").toString(), snapsList.get("eventBatchType").toString(), snapsList.get("eventCourse").toString(),
                                        snapsList.get("eventDate").toString(), snapsList.get("eventTime").toString(), snapsList.get("eventType").toString(),
                                        snapsList.get("eventSubject").toString(), snapsList.get("eventLecturer").toString(), snapsList.get("eventHallName").toString()));
                            }

                            txtEmptyEvents.setEnabled(false);
                            txtEmptyEvents.setVisibility(View.GONE);
                            listView.setEnabled(true);
                            listView.setVisibility(View.VISIBLE);

                            EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
                            listView.setAdapter(listAdapter);
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

    private void getCalenderEvents(){
        calendarView.setEvents(null);
        events = new ArrayList<>();

        customProgressDialog.createProgress();
//         get data from calender collection
        db.collection("Events")
                .whereEqualTo("eventCourse", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentCourse())
                .whereEqualTo("eventBatch", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatch())
                .whereEqualTo("eventBatchType", sharedPreferencesManager.getStudentDataPreferences(SharedPreferencesManager.USER_DETAILS).getStudentBatchType())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            customProgressDialog.dismissProgress();
                            Log.e(TAG, "onSuccess: Empty");
                        }else{
                            DocumentSnapshot snapsList;
                            for(int i = 0; i < task.getResult().getDocuments().size(); i++){
                                snapsList = task.getResult().getDocuments().get(i);

                                String[] dateParts = snapsList.get("eventDate").toString().split("/");

                                Calendar c = Calendar.getInstance();

                                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
                                c.set(Calendar.MONTH, Integer.parseInt(dateParts[1]) - 1);
                                c.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));

                                events.add(new EventDay(c, R.drawable.ic_dot));
                            }
                        }
                        calendarView.setEvents(events);
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

//    // get data from calender collection
//                            db.collection("HallAllocation")
//                                    .whereEqualTo("allocateDate", date)
//                                    .whereEqualTo("course", "")
//                                    .whereEqualTo("batch", "")
//                                    .whereEqualTo("batchType", "")
//                                    .get()
//                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//            if (task.getResult().isEmpty()){
//                Log.e(TAG, "onSuccess: Empty Collection");
//
//                listView.setEnabled(false);
//                listView.setVisibility(View.GONE);
////                                                EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
////                                                listView.setAdapter(listAdapter);
////                                                Log.e(TAG, "onComplete: " + eventsList);
//            }else{
//                DocumentSnapshot snapsList;
//                for(int i = 0; i < task.getResult().getDocuments().size(); i++){
//                    snapsList = task.getResult().getDocuments().get(i);
//                    eventsList.add(new CalenderItem(snapsList.get("allocateDate").toString(), snapsList.get("allocatedTime").toString(), snapsList.get("allocationType").toString(),
//                            snapsList.get("batch").toString(), snapsList.get("batchType").toString(), snapsList.get("course").toString(), snapsList.get("hallName").toString(),
//                            snapsList.get("lecturer").toString(), snapsList.get("subject").toString()));
//                }
//                EventsAdapter listAdapter = new EventsAdapter(getActivity(), eventsList);
//                listView.setAdapter(listAdapter);
//                Log.e(TAG, "onComplete: " + eventsList);
//            }
//        }
//    })
//            .addOnFailureListener(new OnFailureListener() {
//        @Override
//        public void onFailure(@NonNull Exception e) {
//            Log.e(TAG, "onFailure: " + e);
//        }
//    });