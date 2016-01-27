package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skyforlife.projectwithactivity.R;

/**
 * Created by STuotuo.Wen on 2015/12/11.
 */
public class ProfileHeaderFragment extends HeaderFragment {
    private static final String PROFILE_TEXT = "Profile";
    private TextView titleText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.header_item, parent, false);
        titleText = (TextView) v.findViewById(R.id.view_title);
        titleText.setText(PROFILE_TEXT);
        return v;
    }
}
