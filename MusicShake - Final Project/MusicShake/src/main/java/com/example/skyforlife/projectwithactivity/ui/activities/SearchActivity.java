package com.example.skyforlife.projectwithactivity.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.skyforlife.projectwithactivity.ui.fragments.SearchFooterFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.SearchHeaderFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.SearchListFragment;
import com.example.skyforlife.projectwithactivity.utils.Logger;


/**
 * Created by STuotuo.Wen on 2015/11/6.
 */
public class SearchActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        return new SearchHeaderFragment();
    }

    @Override
    protected Fragment createContentFragment() {
        Fragment contentFragment = new SearchListFragment();
        try {
            String searchResult = getIntent().getStringExtra("search_result");
            Bundle bundle = new Bundle();
            bundle.putString("search_result", searchResult);
            contentFragment.setArguments(bundle);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
        }
        return contentFragment;
    }

    @Override
    protected Fragment createFooterFragment() {
        return new SearchFooterFragment();
    }
}
