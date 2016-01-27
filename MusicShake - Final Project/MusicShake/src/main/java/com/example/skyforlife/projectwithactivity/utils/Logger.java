package com.example.skyforlife.projectwithactivity.utils;

import android.util.Log;

/**
 * Created by STuotuo.Wen on 2015/11/22.
 */
public class Logger {
    private static final String[] TAGS = {"[Debug]", "[Error]"};
    /**
     * @param message
     * @param type
     *      0: debug
     *      1: error
     */
    public static void log(String message, int type) {
        switch (type) {
            case 0:
                Log.d(TAGS[0], message);
                break;
            case 1:
                Log.e(TAGS[1], message);
                break;
        }
    }
}
