package com.example.skyforlife.projectwithactivity.entities;

import com.example.skyforlife.projectwithactivity.model.SongRecord;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class SongCache {
    public static LinkedHashMap<Integer, SongRecord> songCache = new LinkedHashMap<Integer, SongRecord>();

    public SongRecord get(int sid) {
        if (songCache.containsKey(sid)) {
            return songCache.get(sid);
        } else {
            return null;
        }
    }

    public List<SongRecord> getAll(String[] sList) throws NumberFormatException {
        List<SongRecord> songList = new LinkedList<SongRecord>();
        for (String sid : sList) {
            songList.add(get(Integer.parseInt(sid)));
        }
        return songList;
    }

    public void put(int sid, SongRecord sr) {
        songCache.put(sid, sr);
    }

    public void clearAll() {
        songCache.clear();
    }
}
