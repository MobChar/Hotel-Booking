package HotelBooking.backend.bussiness.storageServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CustomWindowStorage {
	public static Logger logger = LoggerFactory.getLogger(CustomWindowStorage.class);
	public static String imgPath;
	public static Integer generateId;
	public static String resourcePath;
	public static OutputStream writer;
	public static InputStream reader;
	public static File file;
	static {
		generateId=0;
		
		resourcePath = System.getProperty("user.dir") + "/src/main/resources";
		imgPath = resourcePath + "/images/public";
		logger.warn(imgPath);
		file = new File(resourcePath + "/generateId.txt");
		
		try {
			if (!file.exists()) {
				file.createNewFile();
				saveGeneratedId(writer);
			}
			else {
				reader = new FileInputStream(file);
				generateId = Integer.parseInt(new String(reader.readAllBytes()));
				reader.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String saveImageToStorage(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		logger.warn("File name: " + fileName);
		String fileEx = fileName.split("\\.")[1];
		fileName = (++generateId) + "." + fileEx;

		File wFile = new File(imgPath + "/" + fileName);
		

		try (OutputStream os = new FileOutputStream(wFile)) {
			os.write(file.getBytes());
			saveGeneratedId(writer);
			os.close();
		}
		return fileName;
	}

	public static void saveGeneratedId(OutputStream writer) throws IOException {
		writer = new FileOutputStream(file,false);
		byte[] bytes = generateId.toString().getBytes();
		writer.write(bytes);
		writer.close();
	}
	

}
