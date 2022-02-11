package web.com.itbay.members.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersService {

	@Autowired
	MembersDAO dao;
	
	public MembersDTO login(MembersDTO inputdata) {
		MembersDTO logindata = dao.selectLogin(inputdata);
		return logindata;
	}

	public boolean modifyInfo(MembersDTO logindata) {
		int result = dao.updateModify(logindata);
		return result == 1 ? true : false;
	}

	public MembersDTO getlogindata(int id) {
		MembersDTO dto = dao.imgUpdate_LoginData(id);
		return dto;
	}
	
	public boolean join(MembersDTO membersjoin) {
		
		int result = dao.join(membersjoin);
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
