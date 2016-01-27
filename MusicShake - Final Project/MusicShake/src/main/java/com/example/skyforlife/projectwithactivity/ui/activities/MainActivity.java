package com.example.skyforlife.projectwithactivity.ui.activities;

import android.support.v4.app.Fragment;

import com.example.skyforlife.projectwithactivity.ui.fragments.MainFooterFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.MainHeaderFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.MainListFragment;

public class MainActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        return new MainHeaderFragment();
    }

    @Override
    protected Fragment createContentFragment() {
        return new MainListFragment();
    }

    @Override
    protected Fragment createFooterFragment() {
        return new MainFooterFragment();
    }
}
