package com.anytime.root.api.naverLogin;

import org.springframework.stereotype.Component;

@Component
public final class NaverLoginInfo {
	//client_id: 애플리케이션 등록 후 발급받은 클라이언트 아이디
	//redirect_uri: 네이버 로그인 인증의 결과를 전달받을 콜백 URL(URL 인코딩). 애플리케이션을 등록할 때 Callback URL에 설정한 정보
	//state: 애플리케이션이 생성한 상태 토큰
	public static final String LOCAL_HOST = "http://localhost:8000/root/";
	
	public static final String LOGIN_REDIRECT_URI = LOCAL_HOST+"auth/naver/login/callback";
	public static final String JOIN_REDIRECT_URI = LOCAL_HOST+"auth/naver/join/callback";
	
	public static final String CLIENT_ID ="k1kAtNbr58OggKGStKlK";
	public static final String CLIENT_SECRET = "R0yzAjhKwL";
	public static final String SESSION_STATE = "oauth_state";
	//프로필 조회 API URL
	public static final String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";	
}
