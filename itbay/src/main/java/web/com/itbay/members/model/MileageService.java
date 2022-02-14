package web.com.itbay.members.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MileageService {

	@Autowired
	MileageDAO dao;
	
	public MileageDTO getAmount_mileage(int id) {
		MileageDTO getMileage = dao.selectAmount_mileage(id);
		return getMileage;
	}
}
