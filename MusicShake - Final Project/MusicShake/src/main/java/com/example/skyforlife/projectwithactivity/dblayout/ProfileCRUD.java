package com.example.skyforlife.projectwithactivity.dblayout;

import com.example.skyforlife.projectwithactivity.model.ProfileRecord;
import com.example.skyforlife.projectwithactivity.model.SongRecord;

import java.util.List;

/**
 * Created by STuotuo.Wen on 2015/12/11.
 */
public interface ProfileCRUD {
    public void createProfile(ProfileRecord profile);

    public ProfileRecord readProfile();

    public void updateProfile(String username, ProfileRecord profile);

    public void deleteProfile(String username);
}
