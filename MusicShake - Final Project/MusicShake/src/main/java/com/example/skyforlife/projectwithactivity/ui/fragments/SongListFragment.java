package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.entities.SongLab;
import com.example.skyforlife.projectwithactivity.model.SongRecord;
import com.example.skyforlife.projectwithactivity.service.local.LocalConstants;
import com.example.skyforlife.projectwithactivity.ui.activities.PlayActivity;
import com.example.skyforlife.projectwithactivity.utils.Logger;

import java.util.List;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public abstract class SongListFragment extends ListFragment implements LocalConstants {
    protected List<SongRecord> songRecordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((SongAdapter) getListAdapter()).notifyDataSetChanged();
    }

    protected class SongAdapter extends ArrayAdapter<SongRecord> {
        public SongAdapter(List<SongRecord> songs) {
            super(getActivity(), android.R.layout.simple_list_item_1, songs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.song_item_fragment, null);
            }

            final SongRecord sr = getItem(position);
            final int sid = sr.getSid();

            TextView songNameView =
                    (TextView) convertView.findViewById(R.id.item_name);
            songNameView.setText(sr.getName());

            TextView artistView =
                    (TextView) convertView.findViewById(R.id.artist_name);
            artistView.setText(sr.getSinger());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // go to the play page of clicked song
                    if (SongLab.get(getActivity()).readSong(sid) != null) {
                        // song found in local, play it
                        Intent intent = setupPlayIntent(sr);
                        getActivity().startActivity(intent);
                    } else {
                        // song not found
                        songNotFoundHandling(sr);
                    }
                }
            });

            Button deleteButton = (Button) convertView.findViewById(R.id.delete_item_button);
            setupDeleteButton(deleteButton, sid);

            return convertView;
        }
    }

    protected void setupDeleteButton(Button deleteButton, int sid) {

    }

    protected Intent setupPlayIntent(SongRecord songRecord) {
        Intent intent = new Intent(getActivity(), PlayActivity.class);
        intent.putExtra(SONG_NAME, songRecord.getName());
        intent.putExtra(SONG_FILE, songRecord.getAddress());
        intent.putExtra(COVER_FILE, songRecord.getCover());
        intent.putExtra(SINGER, songRecord.getSinger());
        intent.putExtra(GENRE, songRecord.getGenre());
        intent.putExtra(LENGTH, songRecord.getLength());
        intent.putExtra(SOURCE, getActivity().getClass().getName());
        Logger.log(getActivity().getClass().getSimpleName(), 0);
        return intent;
    }

    protected void songNotFoundHandling(SongRecord songRecord) {

    }
}
