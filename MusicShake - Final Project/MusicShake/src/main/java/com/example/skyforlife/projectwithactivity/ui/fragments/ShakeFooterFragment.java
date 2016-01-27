package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skyforlife.projectwithactivity.ui.activities.SearchActivity;
import com.example.skyforlife.projectwithactivity.ui.activities.ShakeActivity;

/**
 * Created by STuotuo.Wen on 2015/12/7.
 */
public class ShakeFooterFragment extends FooterFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        if (getActivity().getClass().equals(ShakeActivity.class)) {
            shakeButton.setTextColor(Color.WHITE);
        }

        return v;
    }
}
