package cc.cmu.edu.minisite.database;

import cc.cmu.edu.minisite.model.SongRecord;
import java.util.*;

public interface SongHandler {
	public List<SongRecord> searchSongRecord(String pattern);
	public SongRecord getSongRecord(int sid);
}
