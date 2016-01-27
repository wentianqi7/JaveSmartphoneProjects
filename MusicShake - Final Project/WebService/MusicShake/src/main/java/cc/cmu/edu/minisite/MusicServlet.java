package cc.cmu.edu.minisite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import cc.cmu.edu.minisite.model.SongRecord;
import cc.cmu.edu.minisite.services.RequestHandler;
import cc.cmu.edu.minisite.util.Utils;

import java.util.*;

public class MusicServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String searchPattern = request.getParameter("pattern");

		RequestHandler handler = new RequestHandler();
		//String url = handler.getSongUrl(sid);
		List<SongRecord> srList = handler.searchSongRecord(searchPattern);
		if (srList == null) {
			out.write("Not Found");
		} else {
			// convert song record to json and send
			JSONObject outJson = new JSONObject();
			outJson.put("songs", Utils.songListToJson(srList, new JSONArray()));
			out.write(outJson.toString());
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
