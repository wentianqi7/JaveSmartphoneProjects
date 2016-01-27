package com.example.skyforlife.projectwithactivity.service.remote;

import android.content.Context;
import android.os.AsyncTask;

import com.example.skyforlife.projectwithactivity.utils.Logger;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

/**
 * Created by STuotuo.Wen on 2015/11/22.
 */
public class RequestSender extends AsyncTask<String, String, String> {
    private Context appContext;
    public RequestResponse delegate = null;

    public RequestSender(Context context) {
        appContext = context;
    }

    @Override
    protected String doInBackground(String... uri) {
        Logger.log(uri[0], 0);

        try {
            URL url = new URL(uri[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream is = new BufferedInputStream(conn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                StringBuilder sb = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                return sb.toString();
            } else {
                Logger.log("Get failed", 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }
}
