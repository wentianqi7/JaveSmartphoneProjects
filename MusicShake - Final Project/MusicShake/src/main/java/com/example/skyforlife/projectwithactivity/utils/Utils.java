package com.example.skyforlife.projectwithactivity.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.skyforlife.projectwithactivity.entities.ProfileCache;
import com.example.skyforlife.projectwithactivity.entities.SongCache;
import com.example.skyforlife.projectwithactivity.model.ProfileRecord;
import com.example.skyforlife.projectwithactivity.model.SongRecord;

import org.json.*;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class Utils {
    /**
     * retrieve the filename from url
     *
     * @param url input url
     * @return the local file name to store on sdcard
     */
    public static String getFilename(String url) {
        String[] segs = url.trim().split("/");
        return segs[segs.length - 1];
    }

    public static JSONArray sidToJson(List<SongRecord> songList)
            throws JSONException {
        JSONArray srArray = new JSONArray();
        for (SongRecord sr : songList) {
            srArray.put(sr.getSid());
        }
        return srArray;
    }

    public static JSONObject profileToJson(ProfileRecord profile) {
        JSONObject profJson;
        try {
            profJson = new JSONObject();
            profJson.put("username", profile.getUsername());
            profJson.put("location", profile.getLocation());
            profJson.put("email", profile.getEmail());
            profJson.put("image", profile.getImage());
        } catch (Exception e) {
            e.printStackTrace();
            profJson = new JSONObject();
        }
        return profJson;
    }

    public static String cacheSongRecord(SongCache songCache, String result) throws JSONException {
        JSONObject respJson = new JSONObject(result);
        JSONArray songArray = respJson.getJSONArray("songs");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songArray.length(); i++) {
            JSONObject songJson = songArray.getJSONObject(i);

            // create song record object
            int sid = Integer.parseInt(songJson.getString("sid"));
            SongRecord sr = new SongRecord(sid, songJson.getString("name"),
                    songJson.getString("singer"), songJson.getString("cover"), songJson.getString("genre"),
                    songJson.getString("address"), Integer.parseInt(songJson.getString("length")));
            // cache the song record
            songCache.put(sid, sr);
            if (sb.length() > 0) {
                sb.append(";");
            }
            // add song id to string
            sb.append(sid);
        }
        return sb.toString();
    }

    public static String cacheProfile(ProfileCache profileCache, String result) throws JSONException {
        JSONObject respJson = new JSONObject(result);
        JSONObject profJson = respJson.getJSONObject("profile");

        // create profile record object
        String username = profJson.getString("username");
        ProfileRecord pr = new ProfileRecord(username, profJson.getString("location"),
                profJson.getString("email"), profJson.getString("image"));
        // cache the profile record
        profileCache.put(username, pr);

        return username;
    }
}
