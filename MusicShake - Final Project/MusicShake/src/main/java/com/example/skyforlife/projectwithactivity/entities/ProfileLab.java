package com.example.skyforlife.projectwithactivity.entities;

import android.content.Context;

import com.example.skyforlife.projectwithactivity.dblayout.DBAdapter;
import com.example.skyforlife.projectwithactivity.dblayout.ProfileCRUD;
import com.example.skyforlife.projectwithactivity.model.ProfileRecord;

/**
 * Created by STuotuo.Wen on 2015/12/11.
 */
public class ProfileLab implements ProfileCRUD {
    private static ProfileLab profileLab;
    private Context appContext;
    DBAdapter adapter = null;

    public ProfileLab(Context context) {
        appContext = context;
        adapter = new DBAdapter(context);
    }

    public static ProfileLab get(Context context) {
        if (profileLab == null) {
            profileLab = new ProfileLab(context.getApplicationContext());
        }
        return profileLab;
    }

    public void createProfile(ProfileRecord profile) {
        adapter.createProfile(profile);
    }

    public ProfileRecord readProfile() {
        return adapter.readProfile();
    }

    public void updateProfile(String username, ProfileRecord profile) {
        adapter.updateProfile(username, profile);
    }

    public void deleteProfile(String username) {
        adapter.deleteProfile(username);
    }
}
