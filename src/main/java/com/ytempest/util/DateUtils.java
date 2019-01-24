package com.ytempest.util;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class DateUtils {

    public static Date parseDate(String dateStr) {
        Date date = null;
        if (!TextUtils.isEmpty(dateStr)) {
            date = new Date(NumberUtils.parseLong(dateStr));
        }
        return date;
    }
}
