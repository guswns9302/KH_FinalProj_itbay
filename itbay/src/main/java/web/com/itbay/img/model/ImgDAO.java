package web.com.itbay.img.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImgDAO {

	@Autowired
	SqlSession session;
	
	public List<ImgDTO> selectImg(ImgDTO imgDto) {
		List<ImgDTO> imgList = this.session.selectList("ImgMapper.selectImg", imgDto);
		
		return imgList;
	}
}