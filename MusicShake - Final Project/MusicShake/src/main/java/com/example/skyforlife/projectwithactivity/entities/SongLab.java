package com.example.skyforlife.projectwithactivity.entities;

import android.content.Context;
import java.util.*;
import com.example.skyforlife.projectwithactivity.dblayout.SongCRUD;
import com.example.skyforlife.projectwithactivity.dblayout.DBAdapter;
import com.example.skyforlife.projectwithactivity.model.SongRecord;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 */
public class SongLab implements SongCRUD {
    private static SongLab songLab;
    private Context appContext;
    DBAdapter adapter = null;

    public SongLab(Context context) {
        appContext = context;
        adapter = new DBAdapter(context);
    }

    public static SongLab get(Context context) {
        if (songLab == null) {
            songLab = new SongLab(context.getApplicationContext());
        }
        return songLab;
    }

    public void createSong(SongRecord songRecord) {
        adapter.createSong(songRecord);
    }

    public SongRecord readSong(int sid) {
        return adapter.readSong(sid);
    }

    public List<SongRecord> getAllSongs() {
        List<SongRecord> songs = adapter.getAllSongs();
        return songs;
    }

    public void updateSong(int sid, SongRecord newRecord) {
        adapter.updateSong(sid, newRecord);
    }

    public void deleteSong(int sid) {
        adapter.deleteSong(sid);
    }
}
