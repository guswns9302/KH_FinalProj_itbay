package web.com.itbay.members.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.img.model.ImgDTO;

@Repository
public class MileageDAO {

	@Autowired
	SqlSession session;

	public MileageDTO selectAmount_mileage(int id) {
		MileageDTO amount_mileage = this.session.selectOne("MileageMapper.selectAmountMileage",id);
		return amount_mileage;
	}


}
