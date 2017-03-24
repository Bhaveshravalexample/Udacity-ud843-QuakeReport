package com.oz_heng.apps.android.quakereport;

/**
 * Created by Pack Heng on 22/03/17
 * pack@oz-heng.com
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Helper methods related to the Internet connectivity.
 */
public final class ConnectivityUtils {

    private ConnectivityUtils() {
    }

    /**
     * Check the network connectivity.
     *
     * @param context the context of the activity
     * @return true if there's a network connection
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
