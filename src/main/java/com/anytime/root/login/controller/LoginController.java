package com.anytime.root.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anytime.root.kakaoLogin.service.KaKaoLoginService;
import com.anytime.root.kakaoLogin.service.KakaoLoginInfo;
import com.anytime.root.login.service.LoginService;
import com.anytime.root.script.ScriptUtils;
import com.anytime.root.user.dto.KakaoProfile;
import com.anytime.root.user.dto.UserDTO;

@Controller
public class LoginController {
	@Autowired
	KaKaoLoginService kkls;

	@Autowired
	LoginService ls;

	@GetMapping(value = "auth/kakao/login/callback")
	public void kakaoJoin(@RequestParam("code") String code, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {

		// 카카오 프로파일 정보랑 DB랑 이메일 비교를 해야함
		KakaoProfile kakaoProfile = kkls.intergration(code, KakaoLoginInfo.Loginredirect_uri);
		String email = kakaoProfile.getKakao_account().getEmail();
		String nickName = kakaoProfile.getProperties().getNickname();

		System.out.println("로그인한 아이디 : " + email);
		
		// 여기는 카카오 자동로그인이니 패스워드 확인은 하지않아도 된다
		int resultNum = ls.loginCheckIdDto(email, null,session, "login");

		if (resultNum == 1) {
			session.setAttribute("userId", email);
			session.setAttribute("userNickname", nickName);

			// 로그인 성공, 메인으로 보내는것은 똑같음
			ScriptUtils.alertAndMovePage(response, "로그인 완료했습니다.", "/root/index");
		}

		// 로그인 안됐을때 (alret 창 나중에 띄어주기)
		ScriptUtils.alertAndMovePage(response, "없는 아이디입니다 회원가입시도해주세요.", "/root/loginView");
	}

	@RequestMapping(value = "loginView")
	public String LoginView() {
		return "login/loginView";
	}

	@GetMapping("auth/kakao/logout")
	public String Logout(HttpSession session) {
		session.invalidate(); // 전부삭제
		return "redirect:/";
	}
	@GetMapping("generalUser/logout")
	public String GeneralLogout(HttpSession session) {
		/*
		 * session.removeAttribute("userId"); session.removeAttribute("userSchool");
		 * session.removeAttribute("userNickname");
		 * session.removeAttribute("userschoolname");
		 * session.removeAttribute("loginuserAuth");
		 */
		session.invalidate(); // 전부삭제
		return "redirect:/";
	}
	

	@GetMapping(value = "generalLoginView")
	public String GeneralLoginView() {
		return "login/generalLoginView";
	}

	@GetMapping(value = "GeneralLogin")
	public void GeneralLogin(@RequestParam("email") String email, @RequestParam("pwd") String pwd,
			HttpServletResponse response, Model model, HttpSession session) throws IOException {
		
		// 일반회원 가입 로직 작성
		int resultNum = ls.loginCheckIdDto(email, pwd,session, "generallogin");

		if (resultNum == 2) {
			session.setAttribute("userId", email);
			

			// 로그인 성공, 메인으로 보내는것은 똑같음
			ScriptUtils.alertAndMovePage(response, "로그인 완료했습니다.", "/root/index");
		}else if (resultNum == -1) {
			ScriptUtils.alertAndMovePage(response, "비밀번호가 틀렸습니다.", "/root/loginView");
		}else if(resultNum == 7) {
			ScriptUtils.alertAndMovePage(response, "안녕하세요 관리자님.", "/root/MemberManagement");
		}

		// 로그인 안됐을때 (alret 창 나중에 띄어주기)
		ScriptUtils.alertAndMovePage(response, "없는 아이디입니다 회원가입시도해주세요.", "/root/loginView");
	}
	
	//로그인단에 유저정보확인두는건 별로 좋지않지만 일단 넣음
	
	@GetMapping(value = "myinfo/{userId}")
	public String MyUserInfoView(@PathVariable("userId")String email, Model model) {
		
		// 유저 select
		model.addAttribute("userDto", ls.MyUserInfo(email));
		
		return "UserView/MyUserInfo";
	}
	

}
