package HotelBooking.backend.bussiness.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindHotelBody {
	public float latitude, longitude, maxDistant;
	public FindHotelBody(@JsonProperty("lat") float latitude, @JsonProperty("long") float longitude, @JsonProperty("dis") float maxDistant){
		this.latitude=latitude;
		this.longitude=longitude;
		this.maxDistant=maxDistant;
	}
}
