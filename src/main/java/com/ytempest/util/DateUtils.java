package com.ytempest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class DateUtils {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public static Date parseDate(String dateStr) {
        Date date = null;
        if (!TextUtils.isEmpty(dateStr)) {
            date = new Date(NumberUtils.parseLong(dateStr));
        }
        return date;
    }

    public static Date stringToDate(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                return FORMAT.parse(date);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public static long stringToLong(String date) {
        if (!TextUtils.isEmpty(date)) {
            return stringToDate(date).getTime();
        }
        return -1;
    }

}
