package web.com.itbay.img.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
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

	public boolean updateProfileImg(ImgDTO login_img_dto) {
		int res = imgDao.updateProfileImg(login_img_dto);
		if(res == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
