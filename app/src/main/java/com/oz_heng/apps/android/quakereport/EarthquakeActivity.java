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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity {
    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** URL to query the USGS dataset for earthquake information */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
    ListView earthquakeListView = null;
    EarthquakeAdapter earthquakeAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Get the list of earthquakes
        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute(USGS_REQUEST_URL);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            return QueryUtils.fetchEarthquakeData(urls[0]);
        }

        protected void onPostExecute(List<Earthquake> earthquakeList) {
            earthquakes = (ArrayList<Earthquake>) earthquakeList;

            // Find a reference to the {@link ListView} in the layout
            earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new adapter that takes the list of earthquakes as input
            earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this, earthquakes);

            // Set the adapter on the {@link ListView} so the list can be populated in
            // the user interface
            earthquakeListView.setAdapter(earthquakeAdapter);

            // Set a click listener to open the webpage url to see additional details on the
            // earthquake the user clicks on
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    // Find the current earthquake that was clicked on
                    Earthquake earthquake = earthquakeAdapter.getItem(i);

                    // Create an implicit itent to view the earthquake URI
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquake.getUrl()));

                    // Send the intent to launch a new activity
                    startActivity(intent);
                }
            });
        }
    }
}
