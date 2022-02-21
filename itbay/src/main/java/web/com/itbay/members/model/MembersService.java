package web.com.itbay.members.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersService {

	@Autowired
	MembersDAO dao;
	private SqlSessionTemplate userSqlSessin;
		
	
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
	

	/* ID중복 Service 로직 */
	public int idCheck(String nickname) {

		int result = dao.idCheck(nickname);
		return result;				
	}
	
	public String findId(MembersDTO findIdcheck) {
		String result = dao.findId(findIdcheck);
		return result;				
	}
	
	public String findPw(MembersDTO findPwcheck) {
		String result = dao.findPw(findPwcheck);
		return result;
	}

	public boolean deleteMember(MembersDTO membersDTO){
		boolean isSuccess = false;
		
		try {
			if(dao.deleteMember(membersDTO) > 0) {
				System.out.println("success!!!");
				isSuccess = true;
			}else {
				System.out.println("fail!!!");
				isSuccess = false;
			}
		} catch (Exception e){
			System.out.println("error :" + e);
		}
		return isSuccess;
	}
}
