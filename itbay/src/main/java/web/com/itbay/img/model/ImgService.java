package web.com.itbay.img.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgService {

	@Autowired
	ImgDAO imgDao;
	
	public List<ImgDTO> selectImg(int product_id){

		ImgDTO imgDto = new ImgDTO();
		imgDto.setProduct_id(product_id);
		
		return imgDao.selectImg(imgDto);
		
	}
}
