package HotelBooking.backend.persistant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(columnDefinition = "nvarchar(256)")
	private String name;
	@NotNull
	@Column(columnDefinition = "nvarchar(256)")
	private String address;
	@Column(columnDefinition = "nvarchar(2048)")
	private String description;
	
	private float latitude;
	private float longitude;
	
	@NotNull
	@ManyToMany
	private List<PlaceComponent> placeComponents;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;
	
	
	@NotNull
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinTable(name="preview_images",joinColumns = @JoinColumn(
            name = "hotel",
            referencedColumnName = "id"
    ),
    inverseJoinColumns = @JoinColumn(
            name = "preview_image",
            referencedColumnName = "id"
    ))
	private List<Image> previewImages;
	

	

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Facility> facilities;

	@NotNull
	@OneToMany(fetch = FetchType.LAZY,mappedBy="hotel")
	private List<Room> rooms;
	
	@NotNull
	@OneToMany(fetch = FetchType.LAZY,mappedBy="hotel")
	private List<Comment> comments;

	
	
	public Hotel() {
	}

	public Hotel(String name, String address, float latitude, float longitude,String description, Image image, Category category
			,List<Facility> facilities,
			 List<PlaceComponent> placeComponents
			) {
		super();
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.category = category;
		this.facilities = facilities;
		this.description=description;
		this.rooms=new ArrayList<Room>();
		this.previewImages=new ArrayList<Image>();
		this.placeComponents=placeComponents;
	}
	
	public Hotel(String name, String address, float latitude, float longitude,String description, Image image, Category category
			,List<Facility> facilities
			,List<Room> rooms
			,List<Image>previewImages,
			 List<PlaceComponent> placeComponents
			) {
		super();
		this.description=description;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
		this.category = category;
		this.rooms=rooms;
		this.facilities = facilities;
		this.previewImages=previewImages;
		this.rooms=rooms;
		this.placeComponents=placeComponents;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

//	public void setFacility(List<Facility> facilities) {
//		this.facilities = facilities;
//	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Image> getPreviewImages() {
		return previewImages;
	}

	public void setPreviewImages(List<Image> previewImages) {
		this.previewImages = previewImages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<PlaceComponent> getPlaceComponents() {
		return placeComponents;
	}

	public void setPlaceComponents(List<PlaceComponent> placeComponents) {
		this.placeComponents = placeComponents;
	}

	
	
	

}

