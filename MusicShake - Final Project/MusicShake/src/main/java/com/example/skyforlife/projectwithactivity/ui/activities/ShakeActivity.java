package com.example.skyforlife.projectwithactivity.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.ui.fragments.SearchListFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ShakeEmptyFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ShakeFooterFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ShakeHeaderFragment;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.utils.NavigateHelper;
import com.example.skyforlife.projectwithactivity.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/6.
 */
public class ShakeActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        Fragment headerFragment = new ShakeHeaderFragment();
        try {
            String username = getIntent().getStringExtra("username");
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            headerFragment.setArguments(bundle);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
        }
        return headerFragment;
    }

    @Override
    protected Fragment createContentFragment() {
        Fragment contentFragment = null;
        if (getIntent().hasExtra("search_result")) {
            contentFragment = new SearchListFragment();
            String searchResult = getIntent().getStringExtra("search_result");
            Bundle bundle = new Bundle();
            bundle.putString("search_result", searchResult);
            contentFragment.setArguments(bundle);
        } else {
            contentFragment = new ShakeEmptyFragment();
        }
        return contentFragment;
    }

    @Override
    protected Fragment createFooterFragment() {
        return new ShakeFooterFragment();
    }
}
