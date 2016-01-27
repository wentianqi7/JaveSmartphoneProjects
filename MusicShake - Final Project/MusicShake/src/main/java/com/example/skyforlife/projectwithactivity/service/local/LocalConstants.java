package com.example.skyforlife.projectwithactivity.service.local;

/**
 * Created by STuotuo.Wen on 2015/11/23.
 */
public interface LocalConstants {
    String TITLE = "MusicShake";
    String DNS = "http://ec2-54-174-114-145.compute-1.amazonaws.com:8080/MusicShake/";
    String LOGIN_QUERY = "%slogin?username=%s&password=%s&login=%d";
    String SEARCH_QUERY = "%smusic?pattern=%s";
    String SHAKE_QUERY = "%sshake?longitude=%f&latitude=%f&profile=%s&songs=%s";

    // local storage address
    String MUSIC_ADDRESS = "/sdcard/MusicShake/Song/Music/%s";
    String COVER_ADDRESS = "/sdcard/MusicShake/Song/Cover/%s";

    // show download progress info
    int UPDATE_PROGRESS = 8888;
    int MAX_PROGRESS = 100;
    String PROGRESS_TAG = "progress";

    // show play progress info
    int PLAY_PROGRESS = 8889;

    // download intent fields
    String SONG_URL = "song_url";
    String COVER_URL = "cover_url";
    String SONG_FILE = "song_file";
    String COVER_FILE = "cover_file";
    String RECEIVER = "receiver";

    // play music intent fields
    String SOURCE = "source";
    String SONG_NAME = "song_name";
    String SINGER = "singer";
    String GENRE = "song_genre";
    String LENGTH = "length";
}
