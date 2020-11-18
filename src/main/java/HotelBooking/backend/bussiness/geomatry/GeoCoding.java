package HotelBooking.backend.bussiness.geomatry;

import org.springframework.stereotype.Component;

@Component
public interface GeoCoding {
	final int EARTH_RADIUS_IN_METER = 6378137;

	static float rad(float degree) {
		return (float) (degree * Math.PI / 180);
	};

	static float getDistantInMeter(Location start, Location end) {
		float dLat = rad(end.getLatitude() - start.getLatitude());
		float dLong = rad(end.getLongitude() - start.getLongitude());
		float a = (float) (Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(rad(start.getLatitude()))
				* Math.cos(rad(end.getLatitude())) * Math.sin(dLong / 2) * Math.sin(dLong / 2));
		float c = (float) (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
		float d = EARTH_RADIUS_IN_METER * c;
		return d; // returns the distance in meter
	}

}
