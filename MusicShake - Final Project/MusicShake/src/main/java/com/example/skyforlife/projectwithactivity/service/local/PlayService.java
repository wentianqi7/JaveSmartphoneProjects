package com.example.skyforlife.projectwithactivity.service.local;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.skyforlife.projectwithactivity.utils.Logger;

/**
 * Created by STuotuo.Wen on 2015/11/23.
 *
 * play music in a background service
 */
public class PlayService extends Service implements LocalConstants {
    private MediaPlayer mediaPlayer;
    private String curSong;
    private int playbackPosition;

    @Override
    public void onCreate() {
        mediaPlayer = null;
        curSong = null;
        playbackPosition = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String path = null;
        try {
            path = intent.getStringExtra(SONG_FILE);
            if (mediaPlayer == null || !path.equals(curSong)) {
                curSong = path;
                playLocalAudio(path);
            } else if (mediaPlayer.isPlaying()) {
                pauseAudio();
            } else {
                restartAudio();
            }
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    // start a new song
    private void playLocalAudio(String path) throws Exception {
        killMediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    // restart the paused song
    private void restartAudio() {
        mediaPlayer.seekTo(playbackPosition);
        mediaPlayer.start();
    }

    private void pauseAudio() {
        playbackPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                Logger.log(e.toString(), 1);
            }
        }
    }
}
