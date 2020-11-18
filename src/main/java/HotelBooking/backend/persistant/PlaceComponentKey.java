package HotelBooking.backend.persistant;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import HotelBooking.backend.bussiness.repository.PlaceTypeRepository;

public class PlaceComponentKey implements Serializable{

	public String name;
	private Integer placeType;
	public String getName() {
		return name;
	}
	
	
	
	
	public PlaceComponentKey() {
		super();
	}

	public PlaceComponentKey(String name, Integer placeType) {
		super();
		this.name = name;
		this.placeType = placeType;
	}


	public void setName(String name) {
		this.name = name;
	}
//	public PlaceType getPlaceType() {
//		return placeType;
//	}
//	public void setPlaceType(PlaceType placeType) {
//		this.placeType = placeType;
//	}
//	public void setPlaceType(Integer placeType) {
//		this.placeType = placeTypeRepository.getOne(placeType);
//	}




	public Integer getPlaceType() {
		return placeType;
	}




	public void setPlaceType(Integer placeType) {
		this.placeType = placeType;
	}
	
	
	
	
	
}
