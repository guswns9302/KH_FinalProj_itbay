package web.com.itbay;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;



public class UploadFileUtils {

	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	private static String uploadPath = "C:\\uploadFile\\";
	//업로드된 파일을 저장하는 함수
	public static String uploadFile(String originalName, byte[] fileDate) throws IOException {
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;

		
				
		
		File target = new File(uploadPath, savedName);
		makeDir(uploadPath);
		//org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
		FileCopyUtils.copy(fileDate, target);
		
		return savedName;
		
	}
	
	
	//폴더 생성 함수
	private static void makeDir(String uploadPath) {
		
		if(new File(uploadPath).exists()) {
			return;
		}
		

			
		File dirPath = new File(uploadPath);
		
		if(!dirPath.exists()) {
			dirPath.mkdir();
		}

	}

}
