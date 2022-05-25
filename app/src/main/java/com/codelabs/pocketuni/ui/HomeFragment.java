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

import com.applandeo.materialcalendarview.CalendarView;
import com.codelabs.pocketuni.MainActivity;
import com.codelabs.pocketuni.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {

    private ImageView btnCalender;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;
    private CalendarView calendarView;
    private ConstraintLayout bottomSheetContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnCalender = view.findViewById(R.id.btn_calender);

        bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottSheetDialogTheme);
        bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottomsheet_calender, view.findViewById(R.id.bottom_sheet_container));
        bottomSheetContainer = bottomSheetView.findViewById(R.id.bottom_sheet_container);
        calendarView = bottomSheetView.findViewById(R.id.calendarView);

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

}