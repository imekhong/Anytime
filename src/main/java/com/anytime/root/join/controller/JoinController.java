package com.anytime.root.join.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.api.kakaoLogin.KaKaoLoginService;
import com.anytime.root.api.kakaoLogin.KakaoLoginInfo;
import com.anytime.root.api.naverLogin.NaverLoginInfo;
import com.anytime.root.api.naverLogin.NaverLoginService;
import com.anytime.root.join.service.JoinService;
import com.anytime.root.school.service.SchoolService;
import com.anytime.root.user.dto.KakaoProfile;
import com.anytime.root.user.dto.School;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class JoinController {
	private NaverLoginService naverLoginService;
	
	@Autowired
	public JoinController(NaverLoginService naverLoginService) {
		this.naverLoginService = naverLoginService;
	}
	
	@Autowired
	KaKaoLoginService kkls;
	
	@Autowired
	SchoolService schs;
	
	@Autowired
	JoinService js;

	@RequestMapping("selectJoin")
	public String joinView() {
		return "join/selectJoin";
	}
	
	@RequestMapping(value="auth/naver/join")
	public String naverJoin(HttpSession session) {
		return "redirect:"+naverLoginService.getAuthorizationUrl(session, NaverLoginInfo.JOIN_REDIRECT_URI);
	}
	
	@RequestMapping(value="auth/naver/join/callback", method= {RequestMethod.GET, RequestMethod.POST})
	public String naverJoinCallback(Model model, HttpSession session, HttpServletResponse response,
			@RequestParam(value="code", required = false) String code,
			@RequestParam(value="state", required = false) String state,
			@RequestParam(value="error", required = false) String error) throws IOException, ParseException {
		if(error != null) {
			if(error.equals("access_denied")) {
				return "login/loginView";
			}
		}
		OAuth2AccessToken oauthToken = naverLoginService.getAccessToken(session, code, state, NaverLoginInfo.JOIN_REDIRECT_URI);
		// ????????? ????????? ????????? ????????????.
		String apiResult = naverLoginService.getUserProfile(oauthToken, NaverLoginInfo.JOIN_REDIRECT_URI);
		System.out.println(apiResult);
		// String????????? apiResult??? json????????? ????????? ????????? ??????
		// Top?????? ?????? response??? ??????
		JsonObject responseObj = (JsonObject)JsonParser.parseString(apiResult).getAsJsonObject().get("response");
		model.addAttribute("email", responseObj.get("email").toString().replace("\"", ""));
		model.addAttribute("nickname", responseObj.get("name").toString().replace("\"", ""));
		model.addAttribute("agerange",responseObj.get("age").toString().replace("\"", ""));
		model.addAttribute("UserJoinAuth","naverJoin");
		return "join/joinView";
	}

	@GetMapping(value = "auth/kakao/callback")
	public String kakaoJoin(@RequestParam("code") String code, Model model) {
		
		KakaoProfile kakaoProfile =kkls.intergration(code , KakaoLoginInfo.redirect_uri);
		
		model.addAttribute("email", kakaoProfile.getKakao_account().getEmail());
		model.addAttribute("ageGroup", kakaoProfile.getKakao_account().getAge_range());
		model.addAttribute("nickname", kakaoProfile.getProperties().getNickname());
		model.addAttribute("agerange",kakaoProfile.getKakao_account().getAge_range());
		
		model.addAttribute("UserJoinAuth","kakaoJoin");
		// response.getBody(); // body????????? ????????? ???????????? ????????? ???????????? ?????? 
		return "join/joinView";
	}
	
	@PostMapping(value = "saveUser", produces = "application/json;charset=utf-8" )
	@ResponseBody
	public String saveUser(@RequestBody Map<String, Object> user ) {
		
		int resultNum = js.insertUser(user);
		
		System.out.println(resultNum);
		if(resultNum ==0) {
			System.out.println("false???????????????");
			return "{\"test\" : false}";
		}
		
		return "{\"test\" : true}";
	}
	
	@RequestMapping("SearchSchool")
	public String ShowSearchSchool() {
		//System.out.println("????????? ????????? ?????? : " + schoolObject.getDataSearch().getContent().get(0).getSchoolName());
		
		return "join/SearchSchool";
	}
	
	@PostMapping(value = "searchSchoolMap" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchSchoolMap(@RequestBody Map<String, Object> mapDto, Model model) {
		System.out.println(mapDto.get("schoolName"));
		ResponseEntity<String> response = schs.responeSchoolEntity((String)mapDto.get("schoolName"));
		//System.out.println("????????? ??? ?????? : " + response.getBody());
		String searchResult =null;
		String schoolname =null;
		String schoolAddress=null;
		School schoolObject = schs.getSchool(response);
		
		System.out.println("???????????? ??????"+ schoolObject.getDataSearch().getContent().size() );
		if(schoolObject.getDataSearch().getContent().size() ==0) {
			searchResult = "notSchool";
		}else {
			searchResult = "qun"; // ????????? ????????? ???????????????
			// ?????? Map?????? ?????? ??????  select ??????????????? ?????? ????????? ??????????????? ????????????
			Map<String, String> schoolList = new HashMap<String, String>();
			for(int i=0; i < schoolObject.getDataSearch().getContent().size(); i++) {
				System.out.println("???????????? : "+ schoolObject.getDataSearch().getContent().get(i).getSchoolName());
				System.out.println("?????? ?????? : "+schoolObject.getDataSearch().getContent().get(i).getAdres());
				
				schoolname= schoolObject.getDataSearch().getContent().get(i).getSchoolName() ;
				schoolAddress = schoolObject.getDataSearch().getContent().get(i).getAdres();
			}
		}
		
		// ?????? ????????? ???
		return response.getBody();
	}
	
	@GetMapping(value = "generaljoin")
	public String generalJoinView(Model model) {
		// ?????? ?????????????????? ????????? ?????????????????? ???, ????????? ???????????? ????????????
		model.addAttribute("UserJoinAuth","general");
		return "join/joinView";
	}

}
