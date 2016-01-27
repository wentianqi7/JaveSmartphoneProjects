package cc.cmu.edu.minisite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import cc.cmu.edu.minisite.model.ProfileRecord;
import cc.cmu.edu.minisite.model.SongRecord;
import cc.cmu.edu.minisite.services.RequestHandler;
import cc.cmu.edu.minisite.util.Logger;
import cc.cmu.edu.minisite.util.Utils;

public class ShakeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ProfileRecord profile;
		List<Integer> songIds;
		List<SongRecord> songList = new ArrayList<SongRecord>();
		double longitude, latitude;

		// retrieve shaking user location
		try {
			longitude = Double.parseDouble(request.getParameter("longitude"));
		} catch (Exception e) {
			longitude = 0.0;
			// Logger.log(e.toString(), 1);
			e.printStackTrace();
		}

		try {
			latitude = Double.parseDouble(request.getParameter("latitude"));
		} catch (Exception e) {
			latitude = 0.0;
			Logger.log(e.toString(), 1);
		}

		RequestHandler handler = new RequestHandler();

		profile = Utils.JsonToProfile(Utils.strToJson(request
				.getParameter("profile")));
		songIds = Utils.JsonToSongIds(
				Utils.strToJson(request.getParameter("songs")),
				new ArrayList<Integer>());
		for (int sid : songIds) {
			songList.add(handler.getSongRecord(sid));
		}

		// register the shaking event and match it with another
		String reqName = handler.registerShakeEvent(profile, songList);
		// get info of the matched user and write to response
		String username = handler
				.fetchShakingUser(reqName, longitude, latitude);
		JSONObject outJson = new JSONObject();
		if (username != null) {
			outJson.put("songs", Utils.songListToJson(
					handler.getPlayList(username), new JSONArray()));
			outJson.put("profile",
					Utils.profileToJson(handler.getProfile(username)));
		}
		out.write(outJson.toString());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
