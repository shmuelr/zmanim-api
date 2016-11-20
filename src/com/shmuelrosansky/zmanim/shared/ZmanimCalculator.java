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
		
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance(timeZone);
		
		try {
			calendar.setTime(dateFormater.parse(request.getDate()));
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
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a zzzz");
		dateFormater.setTimeZone(timeZone);
		
		response.setAlos(dateFormater.format(zmanimCalendar.getAlosHashachar()));
		response.setSunrise(dateFormater.format(zmanimCalendar.getSunrise()));
		response.setLatestShemaMGA(dateFormater.format(zmanimCalendar.getSofZmanShmaMGA()));
		response.setLatestShemaGRA(dateFormater.format(zmanimCalendar.getSofZmanShmaGRA()));
		response.setLatestShachrisGRA(dateFormater.format(zmanimCalendar.getSofZmanTfilaGRA()));
		response.setChatzos(dateFormater.format(zmanimCalendar.getChatzos()));
		response.setMinchaKetanah(dateFormater.format(zmanimCalendar.getMinchaKetana()));
		response.setMinchaGedolah(dateFormater.format(zmanimCalendar.getMinchaGedola()));
		response.setPlagHamincha(dateFormater.format(zmanimCalendar.getPlagHamincha()));
		response.setSunset(dateFormater.format(zmanimCalendar.getSunset()));
		response.setTzais(dateFormater.format(zmanimCalendar.getTzais()));
		response.setTzais72(dateFormater.format(zmanimCalendar.getTzais72()));
		
		response.locationName = zmanimCalendar.getGeoLocation().getLocationName();
		
		response.status = 1;
		return response;
	}
	
}
