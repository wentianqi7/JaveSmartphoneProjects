package cc.cmu.edu.minisite.database;

import java.util.List;

import cc.cmu.edu.minisite.model.ProfileRecord;
import cc.cmu.edu.minisite.model.SongRecord;

public interface ShakeHandler {
	public String registerShakeEvent(ProfileRecord profile, List<SongRecord> songList);
	public String fetchShakingUser(String username, double longitude, double latitude);
	public List<SongRecord> getPlayList(String username);
	public ProfileRecord getProfile(String username);
}
