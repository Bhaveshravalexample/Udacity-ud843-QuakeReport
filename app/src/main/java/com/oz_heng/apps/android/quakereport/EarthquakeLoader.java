package com.oz_heng.apps.android.quakereport;

import android.content.AsyncTaskLoader;
import android.app.LoaderManager;
import android.content.Context;
import android.util.Log;

import java.util.List;


/**
 * Created by Pack Heng on 20/03/17
 * pack@oz-heng.com
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    /** URL to query the USGS dataset for earthquake information */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";


    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.v(LOG_TAG, "EarthquakeLoader.loadInBackground().");
        return QueryUtils.fetchEarthquakeData(USGS_REQUEST_URL);
    }
}
