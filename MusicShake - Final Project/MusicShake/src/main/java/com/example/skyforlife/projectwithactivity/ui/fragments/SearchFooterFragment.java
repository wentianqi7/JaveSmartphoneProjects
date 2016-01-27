package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skyforlife.projectwithactivity.ui.activities.MainActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.PlayActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.ProfileActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.SearchActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.ShakeActivity;
import com.example.skyforlife.projectwithactivity.utils.GestureHandler;
import com.example.skyforlife.projectwithactivity.utils.NavigateHelper;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class SearchFooterFragment extends FooterFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        if (getActivity().getClass().equals(SearchActivity.class)) {
            searchButton.setTextColor(Color.WHITE);
        }

        return v;
    }
}
