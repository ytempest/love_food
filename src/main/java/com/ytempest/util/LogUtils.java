package com.ytempest.util;

/**
 * @author ytempest
 * Description：
 */
public class LogUtils {

    public static void d(String tag, String msg) {
        System.out.println(tag + ", " + msg);
    }

    public static void e(String tag, String msg) {
        System.err.println(tag + ", " + msg);
    }
}
