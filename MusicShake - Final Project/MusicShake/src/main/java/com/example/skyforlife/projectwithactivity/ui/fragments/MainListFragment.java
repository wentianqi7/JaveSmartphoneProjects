package com.example.skyforlife.projectwithactivity.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.*;

import com.example.skyforlife.projectwithactivity.entities.SongLab;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.ui.activities.MainActivity;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 */
public class MainListFragment extends SongListFragment {
    private List<SongRecord> songRecordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        songRecordList = SongLab.get(getActivity()).getAllSongs();
        SongAdapter adapter = new SongAdapter(songRecordList);
        setListAdapter(adapter);
    }

    @Override
    protected void setupDeleteButton(Button deleteButton, int sid) {
        final int toDel = sid;
        // setup button to delete the song item in list
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongLab.get(getActivity()).deleteSong(toDel);
                Toast.makeText(getActivity(), "delete success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}
