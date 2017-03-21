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

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeActivity.class.getSimpleName();

    /** URL to query the USGS dataset for earthquake information */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";

    // Return the list of {@link Earthquake}s
    private EarthquakeAdapter mEarthquakeAdapter;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private boolean firstDataLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "VERIFY: onCreate() called.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        View emptyView = findViewById(R.id.empty_state_view);

        earthquakeListView.setEmptyView(emptyView);

        // Create a new adapter that takes the list of earthquakes as input
        mEarthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView} so the list can be populated in
        // the user interface
        earthquakeListView.setAdapter(mEarthquakeAdapter);

        // Set a click listener to open the webpage url to see additional details on the
        // earthquake the user clicks on
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Find the current earthquake that was clicked on
                Earthquake earthquake = mEarthquakeAdapter.getItem(i);

                // Create an implicit itent to view the earthquake URI
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquake.getUrl()));

                // Send the intent to launch a new activity
                startActivity(intent);
            }
        });

        // Start {@link EarthquakeLoader} to fetch the earthquake data.

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        Log.v(LOG_TAG, "VERIFY: calling initLoader() ...");
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, "VERIFY: onStart() called ...");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG, "VERIFY: onResume() called ...");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(LOG_TAG, "VERIFY: onPause() called ...");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(LOG_TAG, "VERIFY: onStop() called ...");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.v(LOG_TAG, "VERIFY: onRestart() called ...");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "VERIFY: onDestroy() called ...");
        super.onDestroy();
    }


    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "VERIFY: onCreateLoader() called.");

        // Create a new loader for the given URL
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakeList) {
        Log.v(LOG_TAG, "VERIFY: onLoadFinished() called ...");

        // Clear the adapter of previous earthquake data
        mEarthquakeAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakeList != null && !earthquakeList.isEmpty()) {
            mEarthquakeAdapter.addAll(earthquakeList);
        } else {
            TextView emptyStateView = (TextView) findViewById(R.id.empty_state_view);
            emptyStateView.setText(getResources().getString(R.string.no_earthquake_data));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.v(LOG_TAG, "VERIFY: onLoaderReset() called ...");

        // Loader reset, so we can clear out our existing data.
        mEarthquakeAdapter.clear();
    }
}
