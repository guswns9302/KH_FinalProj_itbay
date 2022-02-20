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
	
	// 서비스 로직 
	public boolean idCheck(String nickname) {
		
		// idCheck 가 데이터베이스와 연결해주는 마이바티스 문법
		int result = dao.idCheck(nickname);
		if(result >= 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String findId(MembersDTO findIdcheck) {
		String result = dao.findId(findIdcheck);
		return result;				
	}
	public String findPw(MembersDTO findPwcheck) {
		String result = dao.findPw(findPwcheck);
		return result;
	}
}
