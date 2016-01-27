package cc.cmu.edu.minisite.util;

import java.util.*;

import org.json.*;

import cc.cmu.edu.minisite.model.ProfileRecord;
import cc.cmu.edu.minisite.model.SongRecord;

public class Utils {
	public static JSONArray songListToJson(List<SongRecord> songList,
			JSONArray srArray) {
		for (SongRecord sr : songList) {
			srArray.put(new JSONObject(sr));
		}
		return srArray;
	}
	
	public static JSONObject profileToJson(ProfileRecord profile) {
		JSONObject profJson;
		try {
			profJson = new JSONObject(profile); 
		} catch (Exception e) {
			e.printStackTrace();
			profJson = new JSONObject();
		}
		return profJson;
	}

	public static JSONObject strToJson(String input) {
		JSONObject json;
		try {
			json = new JSONObject(input);
		} catch (Exception e) {
			json = new JSONObject();
			//Logger.log(e.toString(), 1);
			e.printStackTrace();
		}
		return json;
	}

	public static List<Integer> JsonToSongIds(JSONObject input,
			List<Integer> songIds) {
		try {
			JSONArray sids = input.getJSONArray("songs");
			for (int i = 0; i < sids.length(); i++) {
				songIds.add(sids.getInt(i));
			}
		} catch (Exception e) {
			//Logger.log(e.toString(), 1);
			e.printStackTrace();
		}
		return songIds;
	}

	public static ProfileRecord JsonToProfile(JSONObject input) {
		ProfileRecord profile;
		try {
			profile = new ProfileRecord(input.getString("username"),
					input.getString("location"), input.getString("email"),
					input.getString("image"));
		} catch (Exception e) {
			profile = new ProfileRecord();
			//Logger.log(e.toString(), 1);
			e.printStackTrace();
		}
		return profile;
	}
}
