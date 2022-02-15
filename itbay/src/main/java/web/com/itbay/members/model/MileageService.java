package web.com.itbay.members.model;

import java.util.List;

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

	public boolean chargeMileage(MileageDTO charge_mileage) {
		int charging = dao.insertMileage(charge_mileage);
		if(charging == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<MileageDTO> getHistory_mileage(int id) {
		List<MileageDTO> history_mileage = dao.selectHistoryMileage(id);
		return history_mileage;
	}
}