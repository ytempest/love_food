package com.ytempest.common;

/**
 * @author ytempest
 * Description：
 */
public class LogUtils {

    public static void i(String tag, String msg) {
        System.out.println(tag + ", " + msg);
    }

    public static void e(String tag, String msg) {
        System.err.println(tag + ", " + msg);
    }
}
