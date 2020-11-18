package HotelBooking.backend.bussiness;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import HotelBooking.backend.bussiness.DTO.EditBookingTicketDTO;
import HotelBooking.backend.bussiness.DTO.EditHotelDTO;
import HotelBooking.backend.bussiness.DTO.EditRoomDTO;
import HotelBooking.backend.bussiness.DTO.NewHotelDTO;
import HotelBooking.backend.bussiness.DTO.NewRoomDTO;
import HotelBooking.backend.bussiness.DTO.SuggestHotelDTO;
import HotelBooking.backend.bussiness.repository.AccountRepository;
import HotelBooking.backend.bussiness.repository.BookingTicketRepository;
import HotelBooking.backend.bussiness.repository.CategoryRepository;
import HotelBooking.backend.bussiness.repository.CommentRepository;
import HotelBooking.backend.bussiness.repository.FacilityRepository;
import HotelBooking.backend.bussiness.repository.HotelRepository;
import HotelBooking.backend.bussiness.repository.ImageRepository;
import HotelBooking.backend.bussiness.repository.PlaceRepository;
import HotelBooking.backend.bussiness.repository.PlaceTypeRepository;
import HotelBooking.backend.bussiness.repository.RoomRepository;
import HotelBooking.backend.bussiness.storageServices.CustomWindowStorage;
import HotelBooking.backend.persistant.BookingTicket;
import HotelBooking.backend.persistant.Category;
import HotelBooking.backend.persistant.Facility;
import HotelBooking.backend.persistant.Hotel;
import HotelBooking.backend.persistant.Image;
import HotelBooking.backend.persistant.PlaceComponent;
import HotelBooking.backend.persistant.PlaceComponentKey;
import HotelBooking.backend.persistant.PlaceType;
import HotelBooking.backend.persistant.Room;

@Component
public class Services implements CommandLineRunner {
	public static Logger logger=LoggerFactory.getLogger(Services.class);
	public static String IMAGE_REQ_PREFIX="/image/";
	@Autowired
	BookingTicketRepository bookingTicketRepository;
	@Autowired
	HotelRepository hotelRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	FacilityRepository facilityRepository;
	@Autowired
	CustomWindowStorage storageService;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired 
	CommentRepository commentRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	PlaceTypeRepository placeTypeRepository;
	@Autowired
	PlaceRepository placeRepository;
	

	
	//Image manage
	public Image getDefaultImage() {
		return imageRepository.getOne(1);
	}
	public Image saveImage(MultipartFile imageFile) throws Exception {
		String fileName=IMAGE_REQ_PREFIX+storageService.saveImageToStorage(imageFile);
		return imageRepository.save(new Image(fileName));
	}
	
	//get All
	public List<Facility> getAllFacility(){
		return facilityRepository.findAll();
	}
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	public List<Hotel> getAllHotel(){
		return hotelRepository.findAll();
	}
	
	public List<PlaceComponent> parsepPlaceComponent(String jsonAddressComponent) throws Exception{
		Map<String,String> addCom=JacksonConverter.convert(jsonAddressComponent);
		List<PlaceComponent> placeComponents=new ArrayList<PlaceComponent>();
		for(Map.Entry<String,String> entry: addCom.entrySet()) {
			PlaceType placeType=placeTypeRepository.getPlaceTypeByName(entry.getKey());
			logger.warn(placeType.toString());
			//Check it is a valid place type
			if(placeType!=null) {
				PlaceComponentKey comKey=new PlaceComponentKey(entry.getValue(),placeType.getId());
				PlaceComponent placeCom;
				if(placeRepository.findById(comKey).isEmpty()) {//Try to get Place component
					placeCom=new PlaceComponent(entry.getValue() ,placeType,getDefaultImage());
//					//Check if place component is exist if not create a new address component
					placeRepository.saveAndFlush(placeCom);
					
				}
				else placeCom=placeRepository.getOne(comKey);
			
				placeComponents.add(placeCom);
			}
			else throw new IllegalArgumentException();
		}
		return placeComponents;
		
	}
	
	
	///Hotel manage
	public void saveNewHotel(NewHotelDTO hotelDTO)  throws Exception{
		Image image;
		if(hotelDTO.getThumbImage().isEmpty()) image=getDefaultImage();
		else image=saveImage(hotelDTO.getThumbImage());
		
	
		float[] location=parseLocation(hotelDTO.getLocation());
		Category category=categoryRepository.getOne(hotelDTO.getCategory());
		List<Integer> facIdList=new ArrayList<Integer>();
		String[] facStr=hotelDTO.getFacilities().split(",");
		
		for(int index=0;index<facStr.length;index++) {
			try {
				facIdList.add(Integer.parseInt(facStr[index]));
			}catch (NumberFormatException ex) {
				facIdList.clear();
				break;
			}
		}
		List<Facility> facilities=facilityRepository.findAllById(facIdList);
		
		hotelRepository.save(new Hotel(hotelDTO.getName(),hotelDTO.getAddress(),location[0],location[1],hotelDTO.getDescription(),image,
		category,facilities,parsepPlaceComponent(hotelDTO.getAddressComponent())));
	}
	
	public void editHotel(EditHotelDTO hotelDTO)  throws Exception{
		Hotel hotel=hotelRepository.getOne(hotelDTO.getId());
		
		Image image;
		if(!hotelDTO.getThumbImage().isEmpty())
		{
				image=saveImage(hotelDTO.getThumbImage());
				hotel.setImage(image);
		}
		float[] location=parseLocation(hotelDTO.getLocation());
		Category category=categoryRepository.getOne(hotelDTO.getCategory());
		List<Integer> facIdList=new ArrayList<Integer>();
		String[] facStr=hotelDTO.getFacilities().split(",");
		
		for(int index=0;index<facStr.length;index++) {
			try {
				facIdList.add(Integer.parseInt(facStr[index]));
			}catch (NumberFormatException ex) {
				facIdList.clear();
				break;
			}
		}
		List<Facility> facilities=facilityRepository.findAllById(facIdList);
		//Override
		hotel.setName(hotelDTO.getName());
		hotel.setAddress(hotelDTO.getAddress());
		hotel.setLatitude(location[0]);
		hotel.setLongitude(location[1]);
		hotel.setDescription(hotelDTO.getDescription());
		hotel.setCategory(category);
		hotel.setFacilities(facilities);
		hotel.setPlaceComponents(parsepPlaceComponent(hotelDTO.getAddressComponent()));
		
		hotelRepository.flush();
	}
	
	
	//Room manage
	public void saveNewRoom(NewRoomDTO addDTO)  throws Exception{
		Hotel hotel=hotelRepository.getOne(addDTO.getHotelId());
		Image image;
		if(addDTO.getThumbImage().isEmpty()) image=getDefaultImage();
		else image=saveImage(addDTO.getThumbImage());
	
		Room newRoom=new Room(addDTO.getRoomName(),addDTO.getRoomPrice(),addDTO.getMaxRoom(),image,hotel);
		roomRepository.save(newRoom);
		hotel.getRooms().add(newRoom);
		
		roomRepository.flush();
		hotelRepository.flush();
		
	}
	
	public void editRoom(EditRoomDTO editDTO)  throws Exception{
		Room room=roomRepository.getOne(editDTO.getRoomId());
		
		if(!editDTO.getThumbImage().isEmpty()) {
			Image image=saveImage(editDTO.getThumbImage());
			room.setImage(image);
			
			imageRepository.flush();
			roomRepository.flush();
		}
		if(room.getMaxRoom()>editDTO.getMaxRoom()) throw new IllegalArgumentException();
		
		room.setMaxRoom(editDTO.getMaxRoom());
		room.setName(editDTO.getRoomName());
		room.setPrice(editDTO.getRoomPrice());

		roomRepository.flush();
	}
	
	public List<Room> getAllHotelRoom(Integer hotelId) throws Exception{
		Hotel hotel=hotelRepository.getOne(hotelId);
		return hotel.getRooms();
	}
	
	
	//Booking-ticket manage
	public List<BookingTicket> getAllHotelBooking(int hotelId) throws Exception{
		return bookingTicketRepository.getAllHotelBookingTicket(hotelId);
	}
	public void bookingSingleTicket(Integer roomId, String fullName, String idenNum, Date checkIn, Date checkOut, Integer roomNumber) throws Exception{
		if (checkIn.getTime() > checkOut.getTime())
			throw new IllegalArgumentException();
		Room room = roomRepository.getOne(roomId);
		List<Integer> roomNumberList=bookingTicketRepository.bookingCheck(room.getId(), checkIn, checkOut);
		
		if(Arrays.binarySearch(roomNumberList.toArray(), roomNumber)<0) throw new IllegalArgumentException();
		
		
		
			BookingTicket bookingTicket = new BookingTicket(roomNumber,checkIn, checkOut, idenNum,
					fullName, room);
			bookingTicketRepository.save(bookingTicket);
	
		//Save booking Ticket
		bookingTicketRepository.flush();
		
	}
	public void editBookingTicket(EditBookingTicketDTO editDTO) {
		
		if(editDTO.getCheckIn().compareTo(editDTO.getCheckOut())>0) throw new IllegalArgumentException();
		
		BookingTicket ticket=bookingTicketRepository.getOne(editDTO.getBookingTicketId());
		//Check to try order new booking
		
	}
	//Get all available room of hotel in time interval
	public Map<Integer, List<Integer>> getAllHotelAvailableRoom(Integer hotelId, Date checkIn, Date checkOut) throws Exception {
		Map<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
		List<Room> hotelRooms= getAllHotelRoom(hotelId);
		for(Room room: hotelRooms) {
			List<Integer> roomNumberList=bookingTicketRepository.bookingCheck(room.getId(), checkIn, checkOut);
			map.put(room.getId(), roomNumberList);
		}
		return map;
		
	}
	
	
	//Services
	public List<SuggestHotelDTO> getSuggestHotel(String locationStr, float distance, Integer[] categoryIds, Integer[] facilityIds,
			Date checkIn, Date checkOut) throws Exception{
		float[] location=parseLocation(locationStr);
		return hotelRepository.suggestHotel(location[0], location[1], distance, categoryIds, facilityIds,
				facilityIds.length, checkIn, checkOut);
	}
	
	public int getCountAvailableRoomNumber(Integer roomId, java.util.Date checkInUn, java.util.Date checkOutUn) {
		java.sql.Date checkIn=new java.sql.Date(checkInUn.getTime());
		java.sql.Date checkOut=new java.sql.Date(checkOutUn.getTime());
		return roomRepository.getAllAvailableRoomNumber(roomId, checkIn, checkOut).size();
	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		placeTypeRepository.save(new PlaceType("county"));
//		placeTypeRepository.save(new PlaceType("state"));
//		accountRepository.save(new Account("admin","admin","Bui Ba Binh", IMAGE_REQ_PREFIX+"0.jpg"));
////		accountRepository.save(new Account("shipib191@gmail.com","Binh vip 123","https://gamek.mediacdn.vn/thumb_w/690/2019/7/8/1-15625474669018688730.jpg"));
//		categoryRepository.save(new Category("Apartment"));
//		categoryRepository.save(new Category("Hotel"));
//		categoryRepository.save(new Category("Hostel"));
//		categoryRepository.save(new Category("Inn"));
//		categoryRepository.save(new Category("Villa"));
//
//		facilityRepository.save(new Facility("Airport Transfer"));
//		facilityRepository.save(new Facility("Resto Bar"));
//		facilityRepository.save(new Facility("Airport Transfer"));
//		facilityRepository.save(new Facility("Restaurant"));
//		facilityRepository.save(new Facility("Swimming Pool"));
////
//		imageRepository.save(new Image(IMAGE_REQ_PREFIX+"0.jpg"));
//		imageRepository.save(new Image(IMAGE_REQ_PREFIX+"2.jpg"));
////		imageRepository.save(new Image("image/2.jpg"));
////		
//		roomRepository.save(new Room("Binh room 1", 12, 100, imageRepository.getOne(1)));
//		
//		
//	
////		roomRepository.save(new Room("Binh room 2", 12, 100, imageRepository.getOne(1)));
////		
////		
////		
////		// TODO Auto-generated method stub
//		Hotel hotel=new Hotel("Name 3", "Address 1", 10.780672f,106.6696704f,"asd", imageRepository.getOne(1),
//				categoryRepository.getOne(1),facilityRepository.findAll(),roomRepository.findAll(),imageRepository.findAll(),
//				placeRepository.findAll());
//		hotelRepository.save(hotel);
//		
//		commentRepository.save(new Comment(accountRepository.getOne("admin"),"Hello",hotel));
//		accountRepository.save(new ManagerAccount("manager","manager","Bui Ba Binh", IMAGE_REQ_PREFIX+"0.jpg",hotel));
//		//		hotelRepository.save(new Hotel("Name 4", "Address 2", 44.391403f, 26.1157184f, imageRepository.getOne(2),
////				categoryRepository.getOne(1),facilityRepository.findAll(),new ArrayList<Room>(),imageRepository.findAll() ));
////		
////
////		roomRepository.save(new Room("Binh room 1", 12, 100, imageRepository.getOne(1),hotelRepository.getOne(1)));
////		roomRepository.save(new Room("Binh room 2", 12, 100, imageRepository.getOne(1),hotelRepository.getOne(1)));
////		logger.warn("Hotel Room size: "+hotelRepository.getOne(1).getRooms().size());
//		
//		bookingTicketRepository.save(new BookingTicket(1,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now()),"12312312","Binh vip 123",
//				roomRepository.getOne(1)));
//		
//		
//		
//		roomRepository.flush();
//		imageRepository.flush();
//		hotelRepository.flush();
//		categoryRepository.flush();
//		facilityRepository.flush();
//		commentRepository.flush();
//		accountRepository.flush();
//		placeRepository.flush();
	}
	

	public static float[] parseLocation(String locationStr) throws Exception{
		String[] locationParams = locationStr.split(",");
		locationParams[0] = locationParams[0].trim();
		locationParams[1] = locationParams[1].trim();
		
		float lat = Float.parseFloat(locationParams[0]);
		float lon = Float.parseFloat(locationParams[1]);
		
		return new float[] {lat,lon};
	}
	
	public HotelRepository getHotelRepository() {
		return hotelRepository;
	}
	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}
	public FacilityRepository getFacilityRepository() {
		return facilityRepository;
	}
	public CustomWindowStorage getStorageService() {
		return storageService;
	}
	public ImageRepository getImageRepository() {
		return imageRepository;
	}
	public RoomRepository getRoomRepository() {
		return roomRepository;
	}
	public BookingTicketRepository getBookingTicketRepository() {
		return bookingTicketRepository;
	}
	public CommentRepository getCommentRepository() {
		return commentRepository;
	}
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}
	public PlaceTypeRepository getPlaceTypeRepository() {
		return placeTypeRepository;
	}
	public PlaceRepository getPlaceRepository() {
		return placeRepository;
	}
	
	
	
	
	
	
	
	
	
}
