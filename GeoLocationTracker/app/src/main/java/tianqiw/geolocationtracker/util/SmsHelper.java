package tianqiw.geolocationtracker.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by STuotuo.Wen on 2015/11/19.
 *
 * send SMS to the input cell number with given message
 */
public class SmsHelper {
    private static final String NOT_SENT_MSG = "Message cannot be sent.";
    private static final String NOT_DELIVER_MSG = "Message cannot be delivered.";
    private Context appContext;

    public SmsHelper(Context context) {
        appContext = context;
    }

    public void sendMessage(String message, String cellNumber) {
        appContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Send success.", Toast.LENGTH_SHORT).show();
                    default:
                        Toast.makeText(context, "Send failed: " + getResultCode(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new IntentFilter(NOT_SENT_MSG));

        // send SMS message
        PendingIntent sendIntent = PendingIntent.getBroadcast(appContext, 0, new Intent(NOT_SENT_MSG), 0);
        PendingIntent deliverIntent = PendingIntent.getBroadcast(appContext, 0,new Intent(NOT_DELIVER_MSG), 0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(cellNumber, null, message, sendIntent, deliverIntent);
    }
}
