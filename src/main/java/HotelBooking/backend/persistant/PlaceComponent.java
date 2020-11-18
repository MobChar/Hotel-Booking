package HotelBooking.backend.persistant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@IdClass(PlaceComponentKey.class)
@Entity
public class PlaceComponent {
	@Id
	@Column(columnDefinition = "nvarchar(256)")
	private String name;
	@Id
	@ManyToOne
	@JoinColumn(name="type", referencedColumnName="id")
	private PlaceType placeType;
	
	@ManyToMany(mappedBy="placeComponents")
	private List<Hotel> hotels;
	
	@ManyToOne
	@JoinColumn(name="thumb", referencedColumnName="id")
	private Image thumb;
	

	
	public PlaceComponent() {};
	
	
	public PlaceComponent( String name, Image thumb, List<Hotel> hotels, PlaceType placeType) {
		super();
		this.name = name;
		this.thumb = thumb;
		this.hotels = hotels;
		this.placeType = placeType;
	}
	


	public PlaceComponent(String name, PlaceType placeType, Image thumb) {
		super();
		this.name = name;
		this.placeType = placeType;
		this.thumb = thumb;
		hotels=new ArrayList<Hotel>();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getThumb() {
		return thumb;
	}
	public void setThumb(Image thumb) {
		this.thumb = thumb;
	}
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public PlaceType getPlaceType() {
		return placeType;
	}
	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}


	
	
	
	
}
