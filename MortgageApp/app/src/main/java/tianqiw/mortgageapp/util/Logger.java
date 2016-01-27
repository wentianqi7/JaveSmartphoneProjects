package tianqiw.mortgageapp.util;

import android.util.Log;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 */
public class Logger {
    private static final String TAG = "[ERROR]";

    public Logger() {

    }

    public void log(String errorMsg) {
        Log.e(TAG, errorMsg);
    }
}
