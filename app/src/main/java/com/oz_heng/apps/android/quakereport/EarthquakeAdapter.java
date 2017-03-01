package com.oz_heng.apps.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import junit.framework.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pack Heng on 1/03/17
 * pack@oz-heng.com
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View itemView = convertView;
        if(itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Earthquake} located at this position of the list
        Earthquake earthquake = getItem(position);

        TextView magnitude = (TextView) itemView.findViewById(R.id.magnitude);
        magnitude.setText(String.format("%1$.1f", earthquake.getMagnitude()));

        TextView place = (TextView) itemView.findViewById(R.id.place);
        place.setText(earthquake.getPlace());

        TextView date = (TextView) itemView.findViewById(R.id.date);
        date.setText(dateToString(earthquake.getDate()));

        return itemView;
    }

    public String dateToString(Date date) {
        String stringDate = "";

        if (date != null) {
            DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            stringDate = df.format(date);
        }

        return stringDate;
    }

}
