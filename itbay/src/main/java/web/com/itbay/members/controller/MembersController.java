package web.com.itbay.members.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import web.com.itbay.cart.model.CartService;
import web.com.itbay.img.model.ImgDAO;
import web.com.itbay.img.model.ImgDTO;
import web.com.itbay.img.model.ImgService;
import web.com.itbay.members.model.KakaoLoginService;
import web.com.itbay.members.model.MembersDTO;
import web.com.itbay.members.model.MembersService;

@Controller
public class MembersController {

	@Autowired
	MembersService service;

	@Autowired
	KakaoLoginService kakaoservice;

	@Autowired
	ImgService imgservice;

	// 마솔 - 비회원 장바구니 기능 추가 시작
	@Autowired
	CartService cartService;
	// 마솔 - 비회원 장바구니 기능 추가 끝


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "/login";
	}

	// 마솔 - HttpServletRequest request 추가
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String nickname, String pw,
			HttpSession session) {
		MembersDTO inputdata = new MembersDTO(nickname, pw);
		MembersDTO logindata = new MembersDTO();
		logindata = service.login(inputdata);

		if (logindata != null) {
			session.setAttribute("login", true);
			session.setAttribute("loginMember", logindata);

			// 마솔 - 비회원 장바구니 기능 추가 시작
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				List<Integer> idList = new ArrayList<Integer>();
				for (Cookie cookie : cookies) {
					if (!cookie.getName().equals("JSESSIONID")) {
						idList.add(Integer.parseInt(cookie.getValue()));
					}

				}
				cartService.addCart(idList, logindata.getId());
				// 마솔 - 비회원 장바구니 기능 추가 끝
				for (Cookie cookie : cookies) {
					if (!cookie.getName().equals("JSESSIONID")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}

				}
			}

			return "redirect:/";
		} else {
			session.setAttribute("login", false);
			return "/login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/kakao", method = RequestMethod.GET)
	public String kakaologin(HttpServletRequest request, HttpServletResponse response, String code, Model model, HttpSession session) {
		HashMap<String, String> token = kakaoservice.accessToken(code);
		HashMap<String, String> kakaoLogin_Memberinfo = kakaoservice.kakaoMemberInfo(token.get("access_token"));

		session.setAttribute("token", token.get("access_token"));

		MembersDTO kakaoLogindata = new MembersDTO();
		kakaoLogindata.setNickname(kakaoLogin_Memberinfo.get("email"));
		kakaoLogindata.setUsername(kakaoLogin_Memberinfo.get("kakaoNickName"));
		kakaoLogindata.setEmail_address(kakaoLogin_Memberinfo.get("email"));

		if (session.getAttribute("token") != null) {
			session.setAttribute("login", true);
			if (kakaoservice.duplicateLoginData(kakaoLogindata.getNickname())) {
			} else {
				kakaoservice.dataInsert(kakaoLogindata);
			}
			MembersDTO logindata = kakaoservice.getLoginData(kakaoLogindata);
			session.setAttribute("loginMember_img", kakaoLogin_Memberinfo.get("profile_image"));
			session.setAttribute("loginMember", logindata);
			// 마솔 - 비회원 장바구니 기능 추가 시작
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				List<Integer> idList = new ArrayList<Integer>();
				for (Cookie cookie : cookies) {
					if (!cookie.getName().equals("JSESSIONID")) {
						idList.add(Integer.parseInt(cookie.getValue()));
					}

				}
				cartService.addCart(idList, logindata.getId());
				// 마솔 - 비회원 장바구니 기능 추가 끝
				for (Cookie cookie : cookies) {
					if (!cookie.getName().equals("JSESSIONID")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}

				}
			}

		} else {
			session.setAttribute("login", false);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/myinfo", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {

		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData", loginData);

		return "myinfo";
	}

	@RequestMapping(value = "/myinfo", method = RequestMethod.POST, produces = "applicaton/json; charset=utf-8")
	@ResponseBody
	public String profile(HttpSession session, String phone, String address) {
		MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");
		logindata.setPhone(phone);
		logindata.setAddress(address);

		boolean result = service.modifyInfo(logindata);
		JSONObject json = new JSONObject();
		if (result) {
			json.put("status", "success");
			json.put("message", "변경이 완료되었습니다.");
		} else {
			json.put("status", "fail");
			json.put("message", "패스워드 변경을 실패했습니다.");
		}
		return json.toJSONString();
	}

	@RequestMapping(value = "/myinfo/profileImg", method = RequestMethod.POST)
	public String profileIMG_upload(Model model, HttpServletRequest request, MultipartFile file, String mediafile,
			HttpSession session) {
		String saveDirectory = "C:/dev/jee-2021-06/workspace/KH_FinalProj_itbay/itbay/src/main/webapp/resources/img";
		UUID uuid = UUID.randomUUID();
		File saveFile = new File(saveDirectory, uuid.toString() + "_" + file.getOriginalFilename());
		try {
			file.transferTo(saveFile);
			MembersDTO logindata = (MembersDTO) session.getAttribute("loginMember");

			ImgDTO login_img_dto = new ImgDTO();
			login_img_dto.setMembers_id(logindata.getId());
			login_img_dto.setImg_name(uuid.toString() + "_" + file.getOriginalFilename());

			if (imgservice.updateProfileImg(login_img_dto)) {
				logindata = service.getlogindata(logindata.getId());
				session.removeAttribute("loginMember");
				session.setAttribute("loginMember", logindata);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/myinfo";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {

		return "/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(String nickname, String pw, String email_address, String username, java.sql.Date birth,
			String phone, String address, HttpServletResponse res) throws IOException {

		MembersDTO membersjoin = new MembersDTO();
		membersjoin.setNickname(nickname);
		membersjoin.setPw(pw);
		membersjoin.setUsername(username);
		membersjoin.setBirth(birth);
		membersjoin.setPhone(phone);
		membersjoin.setAddress(address);
		membersjoin.setEmail_address(email_address);

		boolean result = service.join(membersjoin);

		if (result == true) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}

		return "redirect:/";

	}

	/* ID중복 Controller */
	@RequestMapping(value = "/user/idCheck", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(@RequestParam("nickname") String nickname) {

		return service.idCheck(nickname);
	}

	@RequestMapping(value = "/findid", method = RequestMethod.GET)
	public String findId(Model model) {

		return "/findid";
	}

	@RequestMapping(value = "/findid", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> findid(String username, String phone) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		MembersDTO findId = new MembersDTO();
		findId.setUsername(username);
		findId.setPhone(phone);

		String result = service.findId(findId);
		map.put("nickname", result);

		return map;
	}

	@RequestMapping(value = "/findpw", method = RequestMethod.GET)
	public String findPw(Model model) {

		return "/findpw";
	}

	@RequestMapping(value = "/findpw", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> findpw(String username, String phone, String nickname) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		MembersDTO findPw = new MembersDTO();
		findPw.setNickname(nickname);
		findPw.setUsername(username);
		findPw.setPhone(phone);

		String result = service.findPw(findPw);
		map.put("pw", result);
		return map;
	}

	@GetMapping(value = "/deleteMember")
	public String deleteMember(Model model,HttpSession session) {
		MembersDTO loginData = (MembersDTO) session.getAttribute("loginMember");
		model.addAttribute("loginData", loginData);
		return "/deleteMember";
	}

	@PostMapping(value = "/deleteMember")
	public String deleteMember(@RequestParam("pw") String pw, @RequestParam("id") int id,HttpSession session) {
		boolean isSuccess = false;
		MembersDTO membersDto = new MembersDTO();
		membersDto.setId(id);
		membersDto.setPw(pw);
		
		isSuccess = service.deleteMember(membersDto);

		if(isSuccess) {
			
			Object object = session.getAttribute("login");
			if(object != null) {
				session.invalidate();
			}
			return "redirect:/";
			
		}else {
			return "/deleteMember";
		}
	}
}
