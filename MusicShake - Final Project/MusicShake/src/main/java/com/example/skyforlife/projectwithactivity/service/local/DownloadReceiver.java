package com.example.skyforlife.projectwithactivity.service.local;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.skyforlife.projectwithactivity.entities.SongLab;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.utils.Utils;

/**
 * Created by STuotuo.Wen on 2015/11/23.
 */
public class DownloadReceiver extends ResultReceiver implements LocalConstants {
    private Context appContext;
    private ProgressDialog progressDialog;
    private SongRecord songRecord;

    public DownloadReceiver(Handler handler, Context context, ProgressDialog progressDialog,
                            SongRecord songRecord) {
        super(handler);
        this.appContext = context;
        this.progressDialog = progressDialog;
        this.songRecord = songRecord;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultCode == UPDATE_PROGRESS) {
            int progress = resultData.getInt(PROGRESS_TAG);
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(MAX_PROGRESS);
            progressDialog.setProgress(progress);
            if (progress == MAX_PROGRESS) {
                // finish downloading
                progressDialog.dismiss();
                // insert the song record into SQLite
                insertSongRecord();
            }
        }
    }

    private void insertSongRecord() {
        String songAddr = String.format(MUSIC_ADDRESS, Utils.getFilename(songRecord.getAddress()));
        String coverAddr = String.format(COVER_ADDRESS, Utils.getFilename(songRecord.getCover()));
        songRecord.setAddress(songAddr);
        songRecord.setCover(coverAddr);
        SongLab.get(appContext).createSong(songRecord);
        Logger.log("new song is added to the database: " + songRecord.getName(), 0);
    }
}
