package HotelBooking.backend.bussiness.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.querydsl.core.annotations.QueryProjection;

import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Facility;
import HotelBooking.backend.persistant.Image;
import net.minidev.json.JSONObject;

public class NewHotelDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Size(min=5, max=256 , message = "Hotel name must contain 5-256 characters")
	private String name;
	@Size(min=5, max=256 , message = "Hotel address must contain 5-256 characters")
	private String address;
	@Size(min=5, max=2048 , message = "Hotel description must contain 5-2048 characters")
	private String description;
	
	@Pattern(regexp = "^([+-]?([0-9]*[.])?[0-9]+)\\s*,\\s*([+-]?([0-9]*[.])?[0-9]+)$", message = "Invalid location format ")
	private String location;
	private MultipartFile thumbImage;
	
	@Range(min = 1, max = 999999999, message = "Please select positive id numbers for category")
	private Integer category;
	private String facilities="";
	@NotNull
	String addressComponent;
	
	
	
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public MultipartFile getThumbImage() {
		return thumbImage;
	}
	public void setThumbImage(MultipartFile thumbImage) {
		this.thumbImage = thumbImage;
	}
	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	public String getAddressComponent() {
		return addressComponent;
	}
	public void setAddressComponent(String addressComponent) {
		this.addressComponent = addressComponent;
		
	}
	
	
	
	
	
	
	
	

}
