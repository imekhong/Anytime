package com.anytime.root.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anytime.root.admin.controller.AdminInfo;
import com.anytime.root.user.dao.UserDAO;
import com.anytime.root.user.dto.UserDTO;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserDAO mapper;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public int loginCheckIdDto(String email, String pwd, HttpSession session, String loginjoin) {
		UserDTO dto = new UserDTO();
		
		dto = mapper.loginUser(email);
		if (dto != null) {
			if (dto.getEmail().equals(AdminInfo.AdminId)) {
				if (dto.getPassWord().equals(pwd)) {
					session.setAttribute("userSchool", dto.getSchool());
					session.setAttribute("userschoolname", splitschoolname(dto.getSchool()));
					session.setAttribute("userNickname", dto.getUsername()); // 유저 네임이 닉네임
					session.setAttribute("loginuserAuth", dto.getAuth());// 해당 사용자가 카카오유저인지 아닌지
					session.setAttribute("userAuth", dto.getPermissions()); // 권한 상태
					return 7;
				}
			} else {
				// 카카오 로그인이니 비밀번호 확인 안해도 괜찮음
				if (loginjoin.equals("login")) {
					session.setAttribute("userSchool", dto.getSchool());
					session.setAttribute("userschoolname", splitschoolname(dto.getSchool()));
					session.setAttribute("userNickname", dto.getUsername()); // 유저 네임이 닉네임
					session.setAttribute("loginuserAuth", dto.getAuth());// 해당 사용자가 카카오유저인지 아닌지
					session.setAttribute("userAuth", dto.getPermissions()); // 권한 상태
				} else if (loginjoin.equals("generallogin")) {
					// 일반유저니깐 비밀번호도 확인하고 들어가야함, 그리고 비밀번호까지 다 맞췄다면 2를 출력하자
					if (encoder.matches(pwd, dto.getPassWord())) {
						session.setAttribute("userSchool", dto.getSchool());
						session.setAttribute("userschoolname", splitschoolname(dto.getSchool()));
						session.setAttribute("userNickname", dto.getUsername()); // 유저 네임이 닉네임
						session.setAttribute("loginuserAuth", dto.getAuth()); // 해당 사용자가 카카오유저인지 아닌지
						session.setAttribute("userAuth", dto.getPermissions()); // 권한 상태
						return 2;
					} else {
						return -1; // 비밀번호만 틀린것
					}
				}
				return 1; // 아이디가 있는것 자체를 무조건 1을 출력해야한다 안그러면 다른 연계된것들도 수정해야함

			}

		}
		return 0; // 아이디 자체가 없는것
	}

	public String splitschoolname(String schoolname) {
		String splitschoolName[] = schoolname.split("고등학교");
		System.out.println(splitschoolName[0]);

		return splitschoolName[0];
	}

	@Override
	public UserDTO MyUserInfo(String email) {
		System.out.println("서비스단의 이메일 : " + email);
		UserDTO dto = new UserDTO();
		dto = mapper.SelectUserInfoCheck(email);
		
		if(dto != null) {
			System.out.println("왜 값 안나옴?"+dto.getEmail()); 
		}else {
			System.out.println("검색결과가 없습니다");
		}
		
		
		
		return dto;
	}

}
