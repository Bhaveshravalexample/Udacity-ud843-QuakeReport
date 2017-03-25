package com.oz_heng.apps.android.quakereport;

import java.util.Date;

/**
 * Created by Pack Heng on 1/03/17
 * pack@oz-heng.com
 */

public class Earthquake {
    private double mMagnitude = 0;
    private String mPlace = "";
    private Date mDate;
    String mUrl = "";

    private static final String SEPARATOR_OF = " of ";


    /**
     * Constructs a new {@link Earthquake} a new object.
     *
     * @param magnitude     Magnitude of the earthquake
     * @param place         Place of the earthquake
     * @param date          Unix time when the earthquake happened
     */
    public Earthquake (double magnitude, String place, Date date, String url) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
        mUrl = url;
    }

    /**
     * Returns the magnitude of the earthquake.
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Returns the earthquake place.
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * Returns the Unix time when the earthquake happened
     */
    public Date getDate() {
        return mDate;
    }

    /**
     * Return a string that is an USGS website page for that earthquake
     */
    public String getUrl() {
        return mUrl;
    }

}
