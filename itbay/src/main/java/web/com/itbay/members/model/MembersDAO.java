package web.com.itbay.members.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.com.itbay.img.model.ImgDTO;

@Repository
public class MembersDAO {

	@Autowired
	SqlSession session;

	public MembersDTO selectLogin(MembersDTO inputdata) {
		MembersDTO logindata = this.session.selectOne("MembersMapper.selectLogin", inputdata);
		return logindata;
	}

	public int insertKakaoLoginData(MembersDTO logindata) {
		int kakaoLoginData = 0;
		kakaoLoginData = this.session.insert("MembersMapper.insertKakaoData", logindata);
		return kakaoLoginData;
	}

	public MembersDTO duplicateLoginData(String nickname) {
		MembersDTO data = this.session.selectOne("MembersMapper.selectDuplicate", nickname);
		return data;
	}

	public MembersDTO selectKakaoLogin(MembersDTO kakaoLogindata) {
		MembersDTO kakaoLogin = this.session.selectOne("MembersMapper.selectKakaoLogin", kakaoLogindata);
		return kakaoLogin;
	}

	public int updateModify(MembersDTO logindata) {
		int result = this.session.update("MembersMapper.updateInfo",logindata);
		return result;
	}

	public MembersDTO imgUpdate_LoginData(int id) {
		MembersDTO updateIMG_logindata = this.session.selectOne("MembersMapper.selectUpdateIMGdata",id);
		return updateIMG_logindata;
	}
	
	public int join(MembersDTO membersjoin) {
		int result = this.session.insert("MembersMapper.JoinMembers",membersjoin);
		
		return result;
	}
	public int idCheck(String nickname) {
		int result = this.session.selectOne("MembersMapper.idCheck",nickname);
		
		return result;
	}
	
	
}
