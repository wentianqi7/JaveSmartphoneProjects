package tianqiw.geolocationtracker.exception;

import android.util.Log;

/**
 * Created by STuotuo.Wen on 2015/11/19.
 */
public class Logger {
    private static final String TAG = "[GeoLocation]";
    public static void log(String message) {
        Log.e(TAG, message);
    }
}
