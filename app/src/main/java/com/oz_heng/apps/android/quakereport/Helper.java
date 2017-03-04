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

    private static DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("h:mm a");

    /**
     * Return a {@link Date} object from a String input
     *
     * @param stringDate a String input following the format "MM dd, yyyy"
     * @return a Date object, or null if the String input is empty
     */
    public static Date stringToDate(String stringDate) {

        Date date = null;

        if (!stringDate.equals("")) {
            try {
                date = dateFormat.parse(stringDate);
                Log.v(LOG_TAG, "stringToDate() - date: " + dateFormat.format(date));
            } catch (ParseException e) {
                Log.e(LOG_TAG, "stringToDate() - unable to parse the String param ");
                e.printStackTrace();
            }
        }

        return date;
    }

    /**
     * Return a string in the format "MMM dd, yyyy" from the {@link Date} param, or
     * an empty string if the date param is null
     *
     * @param date: a {@link Date} param
     * @return a string
     */
    public static String dateToDateString(Date date) {

        return date != null ? dateFormat.format(date) : "";
    }

    /**
     * Return a string in the format "h:mm a" from the {@link Date} param, or
     * an empty string if the date param is null
     *
     * @param date: a {@link Date} param
     * @return a string
     */
    public static String dateTOTimeString(Date date) {

        return date != null ? timeFormat.format(date) : "";
    }

}
