package com.example.skyforlife.projectwithactivity.entities;

import com.example.skyforlife.projectwithactivity.model.ProfileRecord;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/12/10.
 */
public class ProfileCache {
    public static LinkedHashMap<String, ProfileRecord> profileCache = new LinkedHashMap<String, ProfileRecord>();

    public ProfileRecord get(String username) {
        if (profileCache.containsKey(username)) {
            return profileCache.get(username);
        } else {
            return null;
        }
    }

    public void put(String username, ProfileRecord profile) {
        profileCache.put(username, profile);
    }

    public void clearAll() {
        profileCache.clear();
    }
}
