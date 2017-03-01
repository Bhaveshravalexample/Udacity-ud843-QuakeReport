package com.oz_heng.apps.android.quakereport;

/**
 * Created by Pack Heng on 1/03/17
 * pack@oz-heng.com
 */

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class with helper methods
 */
public class Helper {
    private static final String LOG_TAG = Helper.class.getName();


    /**
     * Returns a Date object from a String input
     *
     * @param stringDate a String input following the format "MM dd, yyyy"
     * @return a Date object, or null if the String input is empty
     */
    public static Date stringToDate(String stringDate) {

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;

        if (!stringDate.equals("")) {
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

    /**
     * Returns a {@link String} object from a {@link Date} input.
     *
     * @param date: a {@link Date} input
     * @return a {@link String} object, or an empty string if the input is null
     */
    public static String dateToString(Date date) {
        String stringDate = "";

        if (date != null) {
            DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            stringDate = df.format(date);
        }

        return stringDate;
    }

}
