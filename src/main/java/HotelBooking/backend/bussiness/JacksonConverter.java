package HotelBooking.backend.bussiness;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonConverter {
	public static Map<String, String> convert(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> map = mapper.readValue(jsonString, Map.class);

		return map;

	}
}
