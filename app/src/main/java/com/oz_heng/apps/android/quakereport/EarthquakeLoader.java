package com.oz_heng.apps.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;


/**
 * Created by Pack Heng on 20/03/17
 * pack@oz-heng.com
 */

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getSimpleName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}
     * @param context context of the activity
     * @param url to load the data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Is called in in a backaground thread to fetch earthquake data from USGS server
     * @return
     */
    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Returns the list of earthquakes fetched from the network request to USGS server.
        return QueryUtils.fetchEarthquakeData(mUrl);
    }

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
