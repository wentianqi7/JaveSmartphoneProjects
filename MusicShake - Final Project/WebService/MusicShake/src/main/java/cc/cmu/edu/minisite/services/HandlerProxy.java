package cc.cmu.edu.minisite.services;

import cc.cmu.edu.minisite.database.DBAdapter;
import cc.cmu.edu.minisite.model.ProfileRecord;
import cc.cmu.edu.minisite.model.ShakeEvent;
import cc.cmu.edu.minisite.model.SongRecord;
import cc.cmu.edu.minisite.util.Cache;
import cc.cmu.edu.minisite.util.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class HandlerProxy {
	private DBAdapter adapter = null;
	private Cache cache = null;

	public HandlerProxy() {
		adapter = new DBAdapter();
		cache = new Cache();
	}

	public List<SongRecord> searchSongRecord(String pattern) {
		if (adapter == null) {
			return null;
		}
		return adapter.searchSongRecord(pattern);
	}
	
	public SongRecord getSongRecord(int sid) {
		if (adapter == null) {
			return null;
		}
		return adapter.getSongRecord(sid);
	}

	public String registerShakeEvent(ProfileRecord profile,
			List<SongRecord> songList) {
		String username = profile.getUsername();
		cache.registerShakeEvent(username, profile, songList);
		return username;
	}

	public String fetchShakingUser(String username, double longitude, double latitude) {
		ShakeEvent curEvent = new ShakeEvent(username, System.currentTimeMillis(),
				longitude, latitude);

		for (int i = 0; i < 10; i++) {
			cache.matchEvent(curEvent);
			if (curEvent.getMatch() != null) {
				break;
			}
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				//Logger.log(e.toString(), 1);
				e.printStackTrace();
				break;
			}
		}
		cache.clearEvents();
		return curEvent.getMatch();
	}

	public List<SongRecord> getPlayList(String username) {
		return cache.getSongList(username);
	}

	public ProfileRecord getProfile(String username) {
		return cache.getProfile(username);
	}
	
	public boolean signin(String username, String password) {
		return adapter.signin(username, password);
	}
	
	public boolean signup(String username, String password) {
		return adapter.signup(username, password);
	}
}
