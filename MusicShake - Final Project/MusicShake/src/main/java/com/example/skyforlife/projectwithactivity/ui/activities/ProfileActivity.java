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
import com.example.skyforlife.projectwithactivity.ui.fragments.MainFooterFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.MainHeaderFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.MainListFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ProfileContentFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ProfileFooterFragment;
import com.example.skyforlife.projectwithactivity.ui.fragments.ProfileHeaderFragment;
import com.example.skyforlife.projectwithactivity.utils.NavigateHelper;
import com.example.skyforlife.projectwithactivity.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/6.
 */
public class ProfileActivity extends MultiFragmentActivity {
    @Override
    protected Fragment createHeaderFragment() {
        return new ProfileHeaderFragment();
    }

    @Override
    protected Fragment createContentFragment() {
        return new ProfileContentFragment();
    }

    @Override
    protected Fragment createFooterFragment() {
        return new ProfileFooterFragment();
    }
}