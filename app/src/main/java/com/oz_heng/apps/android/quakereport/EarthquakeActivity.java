/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oz_heng.apps.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake objects
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake(7.2, "San Francisco", stringToDate("Feb 2, 2016")));
        earthquakes.add(new Earthquake(6.1, "London", stringToDate("Jul 20, 2015")));
        earthquakes.add(new Earthquake(3.9, "Tokyo", stringToDate("Nov 10, 2014")));
        earthquakes.add(new Earthquake(5.4, "Mexico City", stringToDate("May 3, 2014")));
        earthquakes.add(new Earthquake(2.8, "Moscow", stringToDate("Jan 31, 2013")));
        earthquakes.add(new Earthquake(4.9, "Rio de Janeiro", stringToDate("Aug 19, 2012")));
        earthquakes.add(new Earthquake(1.6, "Paris", stringToDate("Oct 13, 2011")));

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);
        earthquakeListView.setAdapter(earthquakeAdapter);
    }

    /**
     * Convert a String to a Date
     *
     * @param stringDate a (String) date following the format "MM dd, yyyy"
     * @return (Date)
     */
    public Date stringToDate(String stringDate) {

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;

        if ( !stringDate.equals("") ) {
            try {
                date = df.parse(stringDate);
                Log.v(LOG_TAG, "stringToDate() - date: " + df.format(date));
            } catch (ParseException e) {
                Log.e(LOG_TAG, "stringToDate() - unable to parse the String param ");
                e.printStackTrace();
            }
        }

        return date;
    }
}
