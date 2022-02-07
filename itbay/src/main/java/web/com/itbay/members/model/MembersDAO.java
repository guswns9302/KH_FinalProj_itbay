package web.com.itbay.members.model;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MembersDAO {

	@Autowired
	SqlSession session;

	public MembersDTO selectLogin(MembersDTO inputdata) {
		System.out.println("dao에서 출력 : " + inputdata.getNickname());
		System.out.println("dao에서 출력 : " + inputdata.getPw());
		MembersDTO logindata = this.session.selectOne("MembersMapper.selectLogin", inputdata);
		return logindata;
	}
}
