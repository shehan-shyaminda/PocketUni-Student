package com.codelabs.pocketuni.adapters;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.models.CalenderItem;

import java.util.ArrayList;

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

//        TextView txt_notificationHead = row.findViewById(R.id.txt_bell_head);
//        TextView txt_notificationBody = row.findViewById(R.id.txt_bell_body);
//        TextView txt_notificationDate = row.findViewById(R.id.txt_bell_date);
//
//        try {
//            txt_notificationHead.setText(messageList.get(position).getTitle());
//            txt_notificationBody.setText(messageList.get(position).getDesc());
//            txt_notificationDate.setText("");
//        } catch (Exception ex) {
//            Log.e(TAG, "getView: " + ex.getLocalizedMessage());
//        }

        return row;
    }
}