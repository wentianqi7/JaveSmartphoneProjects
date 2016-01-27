package com.example.skyforlife.projectwithactivity.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by STuotuo.Wen on 2015/11/23.
 */
public class progressDialogCreater {
    private Context appContext;

    public progressDialogCreater(Context context) {
        appContext = context;
    }

    public ProgressDialog createDialog() {
        ProgressDialog progressDialog = new ProgressDialog(appContext);
        progressDialog.setMessage("Downloading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        return progressDialog;
    }
}
