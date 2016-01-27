package com.example.skyforlife.projectwithactivity.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.service.local.DownloadReceiver;
import com.example.skyforlife.projectwithactivity.service.local.DownloadService;
import com.example.skyforlife.projectwithactivity.service.local.LocalConstants;
import com.example.skyforlife.projectwithactivity.service.local.PlayService;
import com.example.skyforlife.projectwithactivity.utils.GestureHandler;
import com.example.skyforlife.projectwithactivity.utils.Logger;

import java.io.File;

public class PlayActivity extends AppCompatActivity implements LocalConstants {
    private View rootView;
    private SongRecord songRecord;
    private TextView nameText;
    private TextView singerText;
    private ImageView coverView;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_play);
        rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);
        nameText = (TextView) findViewById(R.id.song_name);
        singerText = (TextView) findViewById(R.id.singer);
        coverView = (ImageView) findViewById(R.id.cover_image);
        playButton = (Button) findViewById(R.id.play_button);

        // get extra info form intent
        Intent intent = getIntent();
        songRecord = setupSongRecord(intent);
        Class sourceClass = null;
        try {
            sourceClass = Class.forName(intent.getStringExtra(SOURCE));
            rootView.setOnTouchListener(new GestureHandler(PlayActivity.this, sourceClass));
        } catch (ClassNotFoundException e) {
            Logger.log(e.toString(), 1);
            rootView.setOnTouchListener(new GestureHandler(PlayActivity.this, MainActivity.class));
        }

        // setup ui text and image
        nameText.setText(songRecord.getName());
        singerText.setText(songRecord.getSinger());
        File coverFile = new File(songRecord.getCover());
        if (coverFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(coverFile.getAbsolutePath());
            coverView.setImageBitmap(bitmap);
        } else {
            Logger.log("File not found: " + songRecord.getCover(), 1);
        }

        // control play / stop of the song
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, PlayService.class);
                intent.putExtra(SONG_FILE, songRecord.getAddress());
                startService(intent);
            }
        });
    }

    private SongRecord setupSongRecord(Intent intent) {
        return new SongRecord(intent.getStringExtra(SONG_NAME), intent.getStringExtra(SINGER),
                intent.getStringExtra(COVER_FILE), intent.getStringExtra(GENRE),
                intent.getStringExtra(SONG_FILE), intent.getIntExtra(LENGTH, 0));
    }

}
