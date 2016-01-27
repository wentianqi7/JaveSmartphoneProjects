package com.example.skyforlife.projectwithactivity.service.local;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;

import com.example.skyforlife.projectwithactivity.utils.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by STuotuo.Wen on 2015/11/23.
 * <p>
 * provide download service for the song and its corresponding cover image
 */
public class DownloadService extends IntentService implements LocalConstants {
    private static final int BUF_SIZE = 1024;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String songUrlStr = intent.getStringExtra(SONG_URL);
        String coverUrlStr = intent.getStringExtra(COVER_URL);
        String songAddr = intent.getStringExtra(SONG_FILE);
        String coverAddr = intent.getStringExtra(COVER_FILE);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);

        downloadSong(songUrlStr, songAddr, receiver);
        downloadCover(coverUrlStr, coverAddr);
    }

    /**
     * download song and send the progress percent to activity
     *
     * @param songUrlStr url to download from
     * @param songAddr   local filename to store
     * @param receiver   result receiver in the activity that send the download request intent
     */
    private void downloadSong(String songUrlStr, String songAddr, ResultReceiver receiver) {
        try {
            URL songUrl = new URL(songUrlStr);
            URLConnection conn = songUrl.openConnection();
            conn.connect();
            int fileLength = conn.getContentLength();
            // download the file
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            OutputStream outputStream = new FileOutputStream(songAddr);
            byte[] data = new byte[BUF_SIZE];
            long total = 0;
            int count;
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                // show the progress
                Bundle progressBundle = new Bundle();
                progressBundle.putInt(PROGRESS_TAG, (int) (total * MAX_PROGRESS / fileLength));
                receiver.send(UPDATE_PROGRESS, progressBundle);
                outputStream.write(data, 0, count);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Logger.log(e.toString(), 1);
        }

        Bundle resultBundle = new Bundle();
        resultBundle.putInt(PROGRESS_TAG, MAX_PROGRESS);
        receiver.send(UPDATE_PROGRESS, resultBundle);
    }

    /**
     * download cover image of the song
     *
     * @param coverUrlStr url to download from
     * @param coverAddr   local filename to store
     */
    private void downloadCover(String coverUrlStr, String coverAddr) {
        try {
            URL songUrl = new URL(coverUrlStr);
            URLConnection conn = songUrl.openConnection();
            conn.connect();

            // download the file
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            OutputStream outputStream = new FileOutputStream(coverAddr);
            byte[] data = new byte[BUF_SIZE];
            int count;
            while ((count = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, count);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Logger.log(e.toString(), 1);
        }
    }
}
