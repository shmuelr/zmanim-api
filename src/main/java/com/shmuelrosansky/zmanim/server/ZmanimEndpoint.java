package com.shmuelrosansky.zmanim.server;

import com.google.gson.Gson;
import com.shmuelrosansky.zmanim.server.geolocate.TimeZoneUtils;
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
            resp.getWriter().println("Filling in default params");

            double latitude = 34.0522; // Default Los Angeles
            double longitude = -118.2437; // Default Los Angeles

            String cityName = req.getHeader("X-AppEngine-City");
            if (cityName == null || cityName.isEmpty()) {
                cityName = "Log Angeles";
            }

            String latLon = req.getHeader("X-AppEngine-CityLatLong");

            if (latLon != null && !latLon.isEmpty()) {
                latitude = Double.parseDouble(latLon.split(",")[0]);
                longitude = Double.parseDouble(latLon.split(",")[1]);
            }

			 ZmanRequest zmanRequest = new ZmanRequest();
            zmanRequest.setLatitude(latitude);
            zmanRequest.setLongitude(longitude);
            zmanRequest.setName(cityName);
            zmanRequest.setTimeZone(new TimeZoneUtils().getTimeZoneForLocation(latitude, longitude).getID());

			 resp.getWriter().println(gson.toJson(zmanRequest));
			 return;
		}
		
		ZmanRequest zmanRequest = gson.fromJson(zman_request, ZmanRequest.class);
		
		resp.setHeader("Content-Type", "application/json");
		resp.getWriter().println(gson.toJson(ZmanimCalculator.getZmanim(zmanRequest)));
	}
}
