package com.oz_heng.apps.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.oz_heng.apps.android.quakereport.Helper.dateTOTimeString;
import static com.oz_heng.apps.android.quakereport.Helper.dateToDateString;

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
     *
     * @param context: context of the app
     * @param earthquakes: the list of earthquakes, which is the data source of the adpater
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
        magnitude.setText(String.format("%1$.1f", earthquake.getMagnitude()));

        String place = earthquake.getPlace();
        String distance = getContext().getString(R.string.near_the);
        String location = place;

        if (place.contains(SEPARATOR_OF)) {
            distance = place.split(SEPARATOR_OF)[0] + SEPARATOR_OF;
            location = place.split(SEPARATOR_OF)[1];
        }

        TextView locationOffset = (TextView) itemView.findViewById(R.id.location_offset);
        locationOffset.setText(distance);
        Log.d(LOG_TAG,"getView() - distance: " + distance);

        TextView primaryLocation = (TextView) itemView.findViewById(R.id.primary_location);
        primaryLocation.setText(location);
        Log.d(LOG_TAG, "getView - location: " + location);

        TextView date = (TextView) itemView.findViewById(R.id.date);
        date.setText(dateToDateString(earthquake.getDate()));

        TextView time = (TextView) itemView.findViewById(R.id.time);
        time.setText(dateTOTimeString(earthquake.getDate()));

        return itemView;
    }

 }
