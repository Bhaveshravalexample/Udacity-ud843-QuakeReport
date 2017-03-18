package com.oz_heng.apps.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.oz_heng.apps.android.quakereport.Helper.dateToTimeString;
import static com.oz_heng.apps.android.quakereport.Helper.dateToDateString;
import static com.oz_heng.apps.android.quakereport.Helper.doubleToOneDecimalString;

import android.graphics.drawable.GradientDrawable;

/**
 * Created by Pack Heng on 1/03/17
 * pack@oz-heng.com
 */

/**
 * An {@link EarthquakeAdapter} knows how to create a list item for each earthquake
 * in the data source (a list of {@link Earthquake} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getName();

    private static final String SEPARATOR_OF = " of ";


    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *  @param context : context of the app
     * @param earthquakes : the list of earthquakes, which is the data source of the adpater
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given
     * position in the list of earthquakes.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View itemView = convertView;
        if(itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Get the earthquake located at this position of the list
        Earthquake earthquake = getItem(position);

        // Display the eartquake information to the associated text views
        TextView magnitude = (TextView) itemView.findViewById(R.id.magnitude);
        magnitude.setText(doubleToOneDecimalString(earthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String place = earthquake.getPlace();
        String distance = getContext().getString(R.string.near_the);
        String location = place;

        if (place.contains(SEPARATOR_OF)) {
            distance = place.split(SEPARATOR_OF)[0] + SEPARATOR_OF;
            location = place.split(SEPARATOR_OF)[1];
        }

        TextView locationOffset = (TextView) itemView.findViewById(R.id.location_offset);
        locationOffset.setText(distance);

        TextView primaryLocation = (TextView) itemView.findViewById(R.id.primary_location);
        primaryLocation.setText(location);

        TextView date = (TextView) itemView.findViewById(R.id.date);
        date.setText(dateToDateString(earthquake.getDate()));

        TextView time = (TextView) itemView.findViewById(R.id.time);
        time.setText(dateToTimeString(earthquake.getDate()));

        return itemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int colorId = R.color.magnitude1;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                colorId = R.color.magnitude1;
                break;
            case 2:
                colorId = R.color.magnitude2;
                break;
            case 3:
                colorId = R.color.magnitude3;
                break;
            case 4:
                colorId = R.color.magnitude4;
                break;
            case 5:
                colorId = R.color.magnitude5;
                break;
            case 6:
                colorId = R.color.magnitude6;
                break;
            case 7:
                colorId = R.color.magnitude7;
                break;
            case 8:
                colorId = R.color.magnitude8;
                break;
            case 9:
                colorId = R.color.magnitude9;
                break;
            default:
                colorId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), colorId);
    }

 }
