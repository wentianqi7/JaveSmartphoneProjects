package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import com.example.skyforlife.projectwithactivity.entities.SongCache;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.service.local.DownloadReceiver;
import com.example.skyforlife.projectwithactivity.service.local.DownloadService;
import com.example.skyforlife.projectwithactivity.ui.activities.PlayActivity;
import com.example.skyforlife.projectwithactivity.utils.GestureHandler;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.utils.Utils;
import com.example.skyforlife.projectwithactivity.utils.progressDialogCreater;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class SearchListFragment extends SongListFragment {
    private ProgressDialog progressDialog;
    private SongCache songCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new progressDialogCreater(getActivity()).createDialog();

        // set list content
        try {
            String[] resultArr = getArguments().getString("search_result").trim().split(";");
            songCache = new SongCache();
            songRecordList = null;
            songRecordList = songCache.getAll(resultArr);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
            songRecordList = new LinkedList<SongRecord>();
        }

        SongAdapter adapter = new SongAdapter(songRecordList);
        setListAdapter(adapter);
    }

    @Override
    protected void setupDeleteButton(Button deleteButton, int sid) {
        deleteButton.setVisibility(View.GONE);
    }

    @Override
    protected Intent setupPlayIntent(SongRecord songRecord) {
        Intent intent = super.setupPlayIntent(songRecord);
        String songAddr = String.format(MUSIC_ADDRESS, Utils.getFilename(songRecord.getAddress()));
        String coverAddr = String.format(COVER_ADDRESS, Utils.getFilename(songRecord.getCover()));
        intent.putExtra(SONG_FILE, songAddr);
        intent.putExtra(COVER_FILE, coverAddr);
        return intent;
    }

    @Override
    protected void songNotFoundHandling(SongRecord sr) {
        progressDialog.show();
        // send intent to download service to download mp3 and cover image
        Intent intent = new Intent(getActivity(), DownloadService.class);
        intent.putExtra(SONG_URL, sr.getAddress());
        intent.putExtra(COVER_URL, sr.getCover());
        String songAddr = String.format(MUSIC_ADDRESS, Utils.getFilename(sr.getAddress()));
        String coverAddr = String.format(COVER_ADDRESS, Utils.getFilename(sr.getCover()));
        intent.putExtra(SONG_FILE, songAddr);
        intent.putExtra(COVER_FILE, coverAddr);
        intent.putExtra(RECEIVER, new DownloadReceiver(new Handler(), getActivity(), progressDialog, sr));
        getActivity().startService(intent);
    }
}
