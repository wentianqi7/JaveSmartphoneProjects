package com.example.skyforlife.projectwithactivity.ui.activities;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

import com.example.skyforlife.projectwithactivity.R;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 */
public abstract class MultiFragmentActivity extends FragmentActivity {
    protected abstract Fragment createHeaderFragment();
    protected abstract Fragment createContentFragment();
    protected abstract Fragment createFooterFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_fragment);
        FragmentManager manager = getSupportFragmentManager();

        // content fragment
        Fragment listFragment = manager.findFragmentById(R.id.fragmentContainer);
        if (listFragment == null) {
            listFragment = createContentFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, listFragment)
                    .commit();
        }

        // header fragment
        Fragment headerFragment = manager.findFragmentById(R.id.headerContainer);
        if (headerFragment == null) {
            headerFragment = createHeaderFragment();
            if (headerFragment != null) {
                manager.beginTransaction()
                        .add(R.id.headerContainer, headerFragment)
                        .commit();
            }
        }

        // footer fragment
        Fragment footerFragment = manager.findFragmentById(R.id.footerContainer);
        if (footerFragment == null) {
            footerFragment = createFooterFragment();
            manager.beginTransaction()
                    .add(R.id.footerContainer, footerFragment)
                    .commit();
        }
    }
}

