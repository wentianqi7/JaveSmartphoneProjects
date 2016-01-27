package com.example.skyforlife.projectwithactivity.dblayout;

import com.example.skyforlife.projectwithactivity.model.SongRecord;

import java.util.List;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 */
public interface SongCRUD {
    public void createSong(SongRecord songRecord);

    public SongRecord readSong(int sid);

    public List<SongRecord> getAllSongs();

    public void updateSong(int sid, SongRecord newRecord);

    public void deleteSong(int sid);

}
