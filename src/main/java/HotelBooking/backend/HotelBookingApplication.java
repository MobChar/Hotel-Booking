package HotelBooking.backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HotelBookingApplication.class, args);
		
	}

}
