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

	public int insertKakaoProfileImg(ImgDTO imgdto) {
		int kakaoLoginImg = 0;
		kakaoLoginImg = this.session.insert("MembersMapper.insertKakaoProfileImg", imgdto);
		return kakaoLoginImg;
	}
}
