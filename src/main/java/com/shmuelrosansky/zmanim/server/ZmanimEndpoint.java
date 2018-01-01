package com.shmuelrosansky.zmanim.server;

import com.google.gson.Gson;
import com.shmuelrosansky.zmanim.shared.ZmanRequest;
import com.shmuelrosansky.zmanim.shared.ZmanimCalculator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class ZmanimEndpoint extends HttpServlet {

	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String zman_request = req.getParameter("zman_request");
		if(zman_request == null || zman_request.isEmpty()) {
			 resp.getWriter().println("Please include zman_request parameter");
			 ZmanRequest zmanRequest = new ZmanRequest();
			 zmanRequest.setLatitude(34.0522);
			 zmanRequest.setLongitude(-118.2437);
			 resp.getWriter().println(gson.toJson(zmanRequest));
			 return;
		}
		
		ZmanRequest zmanRequest = gson.fromJson(zman_request, ZmanRequest.class);
		
		resp.setHeader("Content-Type", "application/json");
		resp.getWriter().println(gson.toJson(ZmanimCalculator.getZmanim(zmanRequest)));
	}
}
