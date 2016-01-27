package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.entities.SongCache;
import com.example.skyforlife.projectwithactivity.service.remote.RequestResponse;
import com.example.skyforlife.projectwithactivity.service.remote.RequestSender;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.utils.Utils;

import org.json.JSONException;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class SearchHeaderFragment extends HeaderFragment implements RequestResponse {
    private SearchView searchView;
    private TextView titleText;
    private SongCache songCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songCache = new SongCache();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, parent, false);
        // init title
        titleText = (TextView) v.findViewById(R.id.view_title);
        titleText.setText(TITLE);

        // init search view
        searchView = (SearchView) v.findViewById(R.id.search_song_view);
        searchView.onActionViewExpanded();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        // send http request to backend server when search button is clicked
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String pattern) {
                songCache.clearAll();
                String query = String.format(SEARCH_QUERY, DNS, pattern);
                sendRequest(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return v;
    }

    private void sendRequest(String query) {
        try {
            RequestSender sender = new RequestSender(getActivity());
            sender.delegate = this;
            sender.execute(query);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
            Toast.makeText(getActivity(), "Invalid input.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processFinish(String result) {
        if (result == null) {
            Toast.makeText(getActivity(), "Request failed.", Toast.LENGTH_SHORT).show();
        }
        // request to server finished
        Logger.log(result, 0);
        try {
            // update search result list
            Intent intent = new Intent(getActivity(), getActivity().getClass());
            intent.putExtra("search_result", Utils.cacheSongRecord(songCache, result));
            getActivity().startActivity(intent);
        } catch (JSONException e) {
            Logger.log(e.toString(), 1);
            Toast.makeText(getActivity(), "No Result Found.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
