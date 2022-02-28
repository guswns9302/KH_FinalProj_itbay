package web.com.itbay.websocket.chat;

import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChattingController {

	@RequestMapping(value = "/liveChat", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String chatting(HttpSession session, int members_id) {
		System.out.println("포스트 채팅 컨트롤러 실행");
//		MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
//		logindata.setPhone(phone);
//		logindata.setAddress(address);
//
//		boolean result = service.modifyInfo(logindata);
		JSONObject json = new JSONObject();
		json.put("status", "success");
		json.put("message", "컨트롤러 실행 완료.");
//		if (result) {
//			json.put("status", "success");
//			json.put("message", "변경이 완료되었습니다.");
//		} else {
//			json.put("status", "fail");
//			json.put("message", "패스워드 변경을 실패했습니다.");
//		}
		return json.toJSONString();
	}
}
