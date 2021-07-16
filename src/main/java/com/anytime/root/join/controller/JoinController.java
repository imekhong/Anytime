package com.anytime.root.join.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.anytime.root.join.service.JoinService;
import com.anytime.root.kakaoLogin.service.KaKaoLoginService;
import com.anytime.root.kakaoLogin.service.KakaoLoginInfo;
import com.anytime.root.school.service.SchoolService;
import com.anytime.root.user.dto.KakaoProfile;
import com.anytime.root.user.dto.OAuthToken;
import com.anytime.root.user.dto.School;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class JoinController {
	
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

	@GetMapping(value = "auth/kakao/callback")
	public String kakaoJoin(@RequestParam("code") String code, Model model) {
		
		KakaoProfile kakaoProfile =kkls.intergration(code , KakaoLoginInfo.redirect_uri);
		
		model.addAttribute("email", kakaoProfile.getKakao_account().getEmail());
		model.addAttribute("ageGroup", kakaoProfile.getKakao_account().getAge_range());
		model.addAttribute("nickname", kakaoProfile.getProperties().getNickname());
		model.addAttribute("agerange",kakaoProfile.getKakao_account().getAge_range());
		
		model.addAttribute("UserJoinAuth","kakaoJoin");
		// response.getBody(); // body형식의 데이터 타입으로 편하게 리턴할수 있다 
		return "join/joinView";
	}
	
	@PostMapping(value = "saveUser", produces = "application/json;charset=utf-8" )
	@ResponseBody
	public String saveUser(@RequestBody Map<String, Object> user ) {
		
		int resultNum = js.insertUser(user);
		
		System.out.println(resultNum);
		if(resultNum ==0) {
			System.out.println("false준거같은데");
			return "{\"test\" : false}";
		}
		
		return "{\"test\" : true}";
	}
	
	@RequestMapping("SearchSchool")
	public String ShowSearchSchool() {
		//System.out.println("리스트 첫번째 결과 : " + schoolObject.getDataSearch().getContent().get(0).getSchoolName());
		
		return "join/SearchSchool";
	}
	
	@PostMapping(value = "searchSchoolMap" ,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchSchoolMap(@RequestBody Map<String, Object> mapDto, Model model) {
		System.out.println(mapDto.get("schoolName"));
		
		ResponseEntity<String> response = schs.responeSchoolEntity((String)mapDto.get("schoolName"));
		//System.out.println("리스폰 겟 바디 : " + response.getBody());
		
		String searchResult =null;
		String schoolname =null;
		String schoolAddress=null;
		
		School schoolObject = schs.getSchool(response);
		
		
		System.out.println("검색결과 길이"+ schoolObject.getDataSearch().getContent().size() );
		if(schoolObject.getDataSearch().getContent().size() ==0) {
			searchResult = "notSchool";
		}else {
			searchResult = "qun"; // 결과가 있다의 영어따온것
			
			
			// 그냥 Map으로 계속 추가  select 만들어주고 그냥 지도는 참고형으로 보여주자
			Map<String, String> schoolList = new HashMap<String, String>();
			
			for(int i=0; i < schoolObject.getDataSearch().getContent().size(); i++) {
				System.out.println("학교이름 : "+ schoolObject.getDataSearch().getContent().get(i).getSchoolName());
				System.out.println("학교 주소 : "+schoolObject.getDataSearch().getContent().get(i).getAdres());
				
				schoolname= schoolObject.getDataSearch().getContent().get(i).getSchoolName() ;
				schoolAddress = schoolObject.getDataSearch().getContent().get(i).getAdres();
				
				
			}
			
		}
		
		// 학교 데이터 값
		return response.getBody();
	}
	
	@GetMapping(value = "generaljoin")
	public String generalJoinView(Model model) {
		
		// 이게 일반회원인지 아닌지 확인시켜주는 값, 이값이 보내지면 일반회원
		model.addAttribute("UserJoinAuth","general");
		
		return "join/joinView";
	}
	
	

}
