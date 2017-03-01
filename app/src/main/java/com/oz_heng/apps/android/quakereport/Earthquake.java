package com.oz_heng.apps.android.quakereport;

import java.util.Date;

/**
 * Created by Pack Heng on 1/03/17
 * pack@oz-heng.com
 */

public class Earthquake {
    private double mMagnitude = 0;
    private String mPlace = "";
    private Date mDate = null;

    /**
     * Create a new Earthquake object
     *
     * @param magnitude     Magnitude of the earthquake (float)
     * @param place         Nearest place from the earthquake (String)
     * @param date          Date when the earthquake occurs (Date)
     */
    public Earthquake (double magnitude, String place, Date date) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
    }

    /**
     * Return the magnitude of the earthquake
     *
     * @return  (double)
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Return the nearest place from the earthquake
     *
     * @return  (String)
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * Return the date when the earthquake occurs
     *
     * @return  (Date)
     */
    public Date getDate() {
        return mDate;
    }
}
