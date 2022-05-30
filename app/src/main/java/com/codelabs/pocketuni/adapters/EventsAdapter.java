package com.codelabs.pocketuni.adapters;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.models.CalenderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EventsAdapter extends ArrayAdapter<CalenderItem> {
    ArrayList<CalenderItem> eventsList;
    Activity mActivity;

    public EventsAdapter(Activity activity, ArrayList<CalenderItem> eventsList) {
        super(activity, R.layout.row_event, eventsList);
        this.mActivity = activity;
        this.eventsList = eventsList;
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View row = inflater.inflate(R.layout.row_event, null, true);

        TextView txtDate = row.findViewById(R.id.txt_eventDate);
        TextView txtDay = row.findViewById(R.id.txt_eventDay);
        TextView txtMonth = row.findViewById(R.id.txt_eventMonth);
        TextView txtTitle = row.findViewById(R.id.txt_eventTitle);
        TextView txtType = row.findViewById(R.id.txt_eventType);
        TextView txtLecturer = row.findViewById(R.id.txt_eventLecturer);
        TextView txtHall = row.findViewById(R.id.txt_eventHall);
        TextView txtTime = row.findViewById(R.id.txt_eventTime);
        ImageView icEvent = row.findViewById(R.id.event_ic);
//
        try {
            String[] dateParts = eventsList.get(position).getEventDate().split("/");
//            int year = Integer.parseInt(dateParts[2]); //=> 2022
//            int month = Integer.parseInt(dateParts[1]); //=> 05
//            int day = Integer.parseInt(dateParts[0]); //=> 29

            String year = dateParts[2]; //=> 2022
            String month = dateParts[1]; //=> 05
            String date = dateParts[0]; //=> 29

            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));

            Log.e(TAG, "Friday: " + new SimpleDateFormat("EEE").format(c.getTime()).substring(0,3));
            Log.e(TAG, "13: " + date );
            Log.e(TAG, "may: " + new SimpleDateFormat("MMM").format(c.getTime()).substring(0,3));

//
            txtDay.setText(new SimpleDateFormat("EEEE").format(c.getTime()).substring(0,3));
            txtDate.setText(date);
            txtMonth.setText(new SimpleDateFormat("MMM").format(c.getTime()).substring(0,3));
            txtTitle.setText(eventsList.get(position).getEventCourse());
            txtType.setText(eventsList.get(position).getEventType());
            txtLecturer.setText(eventsList.get(position).getEventLecturer());
            txtHall.setText(eventsList.get(position).getEventHallName());
            txtTime.setText(eventsList.get(position).getEventTime());

            Log.e(TAG, "getView: " + eventsList.get(position).getEventType());
            if (eventsList.get(position).getEventType().equals("EXAM")){
                icEvent.setImageResource(R.drawable.ic_exam);
            }else{
                icEvent.setImageResource(R.drawable.ic_lecture);
            }
        } catch (Exception ex) {
            Log.e(TAG, "getView Error: " + ex.getLocalizedMessage());
        }

        return row;
    }
}