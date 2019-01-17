package com.ytempest.util;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class DateUtils {

    public static Date parseDate(String date) {
        return new Date(NumberUtils.parseLong(date));
    }
}
