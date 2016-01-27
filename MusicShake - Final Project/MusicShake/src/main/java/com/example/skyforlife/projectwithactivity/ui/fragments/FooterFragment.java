package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.ui.activities.MainActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.ProfileActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.SearchActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.ShakeActivity;
import com.example.skyforlife.projectwithactivity.utils.NavigateHelper;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 */
public abstract class FooterFragment extends Fragment {
    protected Button playButton;
    protected Button searchButton;
    protected Button shakeButton;
    protected Button profileButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buttons_fragment, parent, false);
        playButton = (Button) v.findViewById(R.id.play_button);
        searchButton = (Button) v.findViewById(R.id.search_button);
        shakeButton = (Button) v.findViewById(R.id.shake_button);
        profileButton = (Button) v.findViewById(R.id.profile_button);

        playButton.setOnClickListener(new NavigateHelper(getActivity(), MainActivity.class));
        searchButton.setOnClickListener(new NavigateHelper(getActivity(), SearchActivity.class));
        shakeButton.setOnClickListener(new NavigateHelper(getActivity(), ShakeActivity.class));
        profileButton.setOnClickListener(new NavigateHelper(getActivity(), ProfileActivity.class));

        return v;
    }

}
