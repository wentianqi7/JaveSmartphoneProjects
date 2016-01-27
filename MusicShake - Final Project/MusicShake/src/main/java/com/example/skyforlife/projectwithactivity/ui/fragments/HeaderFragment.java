package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.service.local.LocalConstants;

/**
 * Created by STuotuo.Wen on 2015/11/24.
 */
public class HeaderFragment extends Fragment implements LocalConstants {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.header_item, parent, false);
        return v;
    }
}
