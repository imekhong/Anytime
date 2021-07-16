package com.anytime.root.join.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anytime.root.login.service.LoginService;
import com.anytime.root.user.dao.UserDAO;
import com.anytime.root.user.dto.UserDTO;

@Service
public class JoinServiceImpl implements JoinService{
	@Autowired
	LoginService ls;
	
	
	@Autowired
	UserDAO mapper;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	//http 클래스를 이용해서 요청하기
	// 먼저 json값을 담을 클래스를 작성 -> 카카오로그인처럼 데이터를 담아낸다
	
	
	
	@Override
	public int insertUser(Map<String, Object> user) {
		UserDTO dto = new UserDTO();
		String securePW =null ;
		String UserJoinAuth = (String)user.get("UserJoinAuth");
		

		dto.setEmail((String)user.get("email"));
		dto.setUsername((String)user.get("nickname")); // 닉네임
		dto.setAge((String)user.get("age"));
		dto.setEnterYear((String)user.get("enterYear"));
		dto.setGrade(Integer.parseInt((String)user.get("grade"))); 
		dto.setSchool((String)user.get("parSchoolName")+" : "+ (String)user.get("parSchooladd")); // 여기 주소값이랑 다담겨있음 . 을 구분으로 split하면될듯
		dto.setAgeGroup((String)user.get("ageGroup"));
		
		if( UserJoinAuth.equals("general")) {
			//암호화 줄
			securePW = encoder.encode((String)user.get("pwd"));
			dto.setPassWord(securePW);
			dto.setAuth("generalUser");
		}else {
			securePW = encoder.encode(randomPW()); // 랜덤한 숫자 네자리를 그냥 암호화해서 넣어버리기
			dto.setAuth("kakaoUser");
			dto.setPassWord(securePW); //카카오 유저는 랜덤한 비밀번호를가지게 해야함 1234는 임시방편
		}
		
		dto.setRule("user");
		dto.setPermissions("false"); // 기본 사용자 권한은 false로 되어있다
		
		// 1이면 아이디가 있는거니깐 0이여야함
		int resultNum = ls.loginCheckIdDto(dto.getEmail(), null,null, "join");
		
		if (resultNum ==1) {
			return 0;
		}
		
		// 가입일로부터 일단 무조건 3년 후
		mapper.insertUser(dto);
		
		return 1;
	}

	@Override
	public String randomPW() {
		String randomPW = null;
		for(int i=0; i <4; i++) {
			
			randomPW += Integer.toString((int)((Math.random()*10000)%10));
		}
		System.out.println("랜덤 비밀번호 4자리임 아무튼 그럼 : "+randomPW);
		
		
		return randomPW;
	}
	
}
