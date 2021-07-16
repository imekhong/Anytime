package com.anytime.root.kakaoLogin.service;

import org.springframework.stereotype.Component;

// 반듯이 component 어노테이션을 붙여줘야한다
@Component
public  final class KakaoLoginInfo {
	// 코드 그대로 쏘는것도 문제니깐 이렇게 클래스에 담아서 jsp에 보내주자
	public static final String localHostName = "http://localhost:8000/";
	public static final String LoginRequestURI = "https://kauth.kakao.com/oauth/authorize?"
			+ "client_id=a924c282a86092b8472e6c2885aafe4a&"
			+ "redirect_uri="+localHostName+"auth/kakao/callback&response_type=code HTTP/1.1";
	// 주의 : 노트북과 데스크탑,등등 작업환경을 변경할때마다 포트번호를알맞게 변경해주세요
	public static final String grant_type ="authorization_code";
	public static final String client_id = "a924c282a86092b8472e6c2885aafe4a";
	public static final String redirect_uri = localHostName+"root/auth/kakao/callback"; // 여기도 맞게 변경바람
	public static final String Loginredirect_uri = localHostName+"root/auth/kakao/login/callback";// 여기도 맞게 변경바람
	public static final String OauthToken_request = "https://kauth.kakao.com/oauth/token"; // 인증토큰주소
	public static final String UserInfoSelect = "https://kapi.kakao.com/v2/user/me"; // 사용자 정보조회에 필요함
	
	public static final String ContentType = "application/x-www-form-urlencoded;charset=utf-8";
	
}
