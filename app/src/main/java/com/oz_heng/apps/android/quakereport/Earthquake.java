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

    private static final String OF = "of";
    private static final String NEAR_THE = "Near the";


    /**
     * Constructs a new {@link Earthquake} a new object.
     *
     * @param magnitude     Magnitude of the earthquake
     * @param place         Place of the earthquake
     * @param date          Unix time when the earthquake happened
     */
    public Earthquake (double magnitude, String place, Date date) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
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
     * Return the subtstring of mPlace up to and is inclusive of the OF string, or the NEAR_THE string if
     * mPlace doesn't include the OF string
     *
     * @return a string
     */
    public String getLocationOffset() {
        String locationOffset = NEAR_THE;

        int index = mPlace.indexOf(OF);
        if (index != -1) {
            locationOffset = mPlace.substring(0, index + OF.length());
        }

        return locationOffset;
    }

    /**
     * Return the substring of mPlace following but excluding the OF string
     *
     * @return a string
     */
    public String getPrimaryLocation() {
        String primaryLocation = mPlace;

        int index = mPlace.indexOf(OF);
        if (index != -1) {
         primaryLocation = mPlace.substring(index + OF.length() +1, mPlace.length());
        }

        return primaryLocation;
    }

}
