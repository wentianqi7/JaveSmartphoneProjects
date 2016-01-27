package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skyforlife.projectwithactivity.R;

/**
 * Created by STuotuo.Wen on 2015/12/7.
 */
public class ShakeEmptyFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_shake_idle, parent, false);
        return v;
    }
}
