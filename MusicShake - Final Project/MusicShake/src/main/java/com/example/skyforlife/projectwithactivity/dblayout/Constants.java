package com.example.skyforlife.projectwithactivity.dblayout;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 */
public interface Constants {
    String DATABASE = "MusicSocial";

    String CREATE_SONG_TABLE = "CREATE TABLE songs" +
            "(sid INTEGER PRIMARY KEY," +
            "name TEXT," +
            "singer TEXT," +
            "cover TEXT," +
            "genre TEXT," +
            "address TEXT,"+
            "length INTEGER);";

    String CREATE_PROFILE_TABLE = "CREATE TABLE profile" +
            "(username TEXT PRIMARY KEY," +
            "location TEXT," +
            "email TEXT," +
            "image TEXT);";
}
