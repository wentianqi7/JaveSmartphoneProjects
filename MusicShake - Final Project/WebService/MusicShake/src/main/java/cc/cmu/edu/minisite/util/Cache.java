package cc.cmu.edu.minisite.util;

import java.util.*;

import cc.cmu.edu.minisite.model.ProfileRecord;
import cc.cmu.edu.minisite.model.ShakeEvent;
import cc.cmu.edu.minisite.model.SongRecord;

public class Cache {
	private static List<ShakeEvent> eventQueue = new ArrayList<ShakeEvent>();
	private static Map<String, ProfileRecord> profileCache = new LinkedHashMap<String, ProfileRecord>();
	private static Map<String, List<SongRecord>> songCache = new LinkedHashMap<String, List<SongRecord>>();

	public void registerShakeEvent(String username, ProfileRecord profile,
			List<SongRecord> songList) {
		synchronized (profileCache) {
			profileCache.put(username, profile);
		}
		synchronized (songCache) {
			songCache.put(username, songList);
		}
	}

	public ProfileRecord getProfile(String username) {
		synchronized (profileCache) {
			return profileCache.get(username);
		}
	}

	public List<SongRecord> getSongList(String username) {
		synchronized (songCache) {
			return songCache.get(username);
		}
	}

	public void matchEvent(ShakeEvent curEvent) {
		synchronized (eventQueue) {
			Iterator<ShakeEvent> it = eventQueue.iterator();
			String match = curEvent.getMatch();
			if (match != null) {
				return;
			}
			// if is not matched yet
			while (it.hasNext()) {
				ShakeEvent prevEvent = it.next();

				if (prevEvent != curEvent && prevEvent.getMatch() == null 
						&& Math.abs(prevEvent.getTimestamp()
								- curEvent.getTimestamp()) < 2000
						&& prevEvent.withinArea(curEvent.getLongitude(),
								curEvent.getLatitude())) {
					prevEvent.setMatch(curEvent.getUsername());
					curEvent.setMatch(prevEvent.getUsername());
					return;
				}
			}
			eventQueue.add(curEvent);
		}
	}

	public void clearEvents() {
		synchronized (eventQueue) {
			long curTime = System.currentTimeMillis();
			Iterator<ShakeEvent> it = eventQueue.iterator();
			while (it.hasNext()) {
				ShakeEvent prevEvent = it.next();
				if (curTime - prevEvent.getTimestamp() > 10000) {
					it.remove();
				}
			}
		}
	}
}
