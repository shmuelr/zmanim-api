package com.shmuelrosansky.zmanim.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import net.sourceforge.zmanim.ZmanimCalendar;
import net.sourceforge.zmanim.util.GeoLocation;

public class ZmanimCalculator {

	public static ZmanResponse getZmanim(ZmanRequest request) {
		ZmanResponse response = new ZmanResponse();
		
		TimeZone timeZone = TimeZone.getTimeZone(request.getTimeZone());

		ZmanimCalendar zmanimCalendar = new ZmanimCalendar(
				new GeoLocation(
						request.getName(),
						request.getLatitude(),
						request.getLongitude(),
						request.getElevation(),
						timeZone
						)
				);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(timeZone);
		
		try {
			calendar.setTime(dateFormatter.parse(request.getDate()));
		} catch (ParseException e) {
			response.status = -1;
			response.errorMessage = "Invalid Date Format";
			return response;
		}
		calendar.setTimeZone(timeZone);

		zmanimCalendar.setCalendar(calendar);

		return buildResponse(zmanimCalendar, timeZone);
	}

	private static ZmanResponse buildResponse(ZmanimCalendar zmanimCalendar, TimeZone timeZone) {
		ZmanResponse response = new ZmanResponse();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a zzzz");
		dateFormatter.setTimeZone(timeZone);
		
		response.setAlos(dateFormatter.format(zmanimCalendar.getAlosHashachar()));
		response.setSunrise(dateFormatter.format(zmanimCalendar.getSunrise()));
		response.setLatestShemaMGA(dateFormatter.format(zmanimCalendar.getSofZmanShmaMGA()));
		response.setLatestShemaGRA(dateFormatter.format(zmanimCalendar.getSofZmanShmaGRA()));
		response.setLatestShachrisGRA(dateFormatter.format(zmanimCalendar.getSofZmanTfilaGRA()));
		response.setChatzos(dateFormatter.format(zmanimCalendar.getChatzos()));
		response.setMinchaKetanah(dateFormatter.format(zmanimCalendar.getMinchaKetana()));
		response.setMinchaGedolah(dateFormatter.format(zmanimCalendar.getMinchaGedola()));
		response.setPlagHamincha(dateFormatter.format(zmanimCalendar.getPlagHamincha()));
		response.setSunset(dateFormatter.format(zmanimCalendar.getSunset()));
		response.setTzais(dateFormatter.format(zmanimCalendar.getTzais()));
		response.setTzais72(dateFormatter.format(zmanimCalendar.getTzais72()));
		
		response.locationName = zmanimCalendar.getGeoLocation().getLocationName();
		
		response.status = 1;
		return response;
	}
	
}
