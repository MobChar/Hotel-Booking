package HotelBooking.backend.bussiness.DTO;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EditBookingTicketDTO extends NewBookingDTO{
	@NotNull
	private Integer bookingTicketId;

	public Integer getBookingTicketId() {
		return bookingTicketId;
	}

	public void setBookingTicketId(Integer bookingTicketId) {
		this.bookingTicketId = bookingTicketId;
	}
	
	
	 
	
}
