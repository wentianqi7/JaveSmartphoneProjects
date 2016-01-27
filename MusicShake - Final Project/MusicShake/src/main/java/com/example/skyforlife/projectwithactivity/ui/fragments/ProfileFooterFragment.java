package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skyforlife.projectwithactivity.ui.activities.ProfileActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.SearchActivity;

/**
 * Created by STuotuo.Wen on 2015/12/11.
 */
public class ProfileFooterFragment extends FooterFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        if (getActivity().getClass().equals(ProfileActivity.class)) {
            profileButton.setTextColor(Color.WHITE);
        }
        return v;
    }
}
