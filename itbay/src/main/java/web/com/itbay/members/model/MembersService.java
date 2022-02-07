package web.com.itbay.members.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersService {

	@Autowired
	MembersDAO dao;
	
	public MembersDTO login(MembersDTO inputdata) {
		System.out.println("서비스에서 출력 : " + inputdata.getNickname());
		System.out.println("서비스에서 출력 : " + inputdata.getPw());
		MembersDTO logindata = dao.selectLogin(inputdata);
		return logindata;
	}
}
