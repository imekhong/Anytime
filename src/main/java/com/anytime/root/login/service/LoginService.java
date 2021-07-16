package com.anytime.root.login.service;

import javax.servlet.http.HttpSession;

import com.anytime.root.user.dto.UserDTO;

public interface LoginService {
	//login단계에서쓰고싶으면 login 아니면join
	public int loginCheckIdDto(String email, String pwd,HttpSession session,String loginjoin); 
	
	// 특정 유저의 정보를 검색,select하게 해주는 서비스메소드
	public UserDTO MyUserInfo(String email);
}
