package com.anytime.root.kakaoLogin.service;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.anytime.root.user.dto.KakaoProfile;
import com.anytime.root.user.dto.OAuthToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoLoginServiceImpl implements KaKaoLoginService {
	
	@Override
	public ResponseEntity<String> responeKEntity(String code , String redirect_uri) {

		// httpsURLConnection url ->옛날 방식, 조금 불편
		// REtrofit2(안드로이드에서쓰는 라이브러리) , OkHttp
		RestTemplate rt = new RestTemplate();

		
		// httpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		// 현재 http형식이 key-value 형식임을 알린다
		headers.add("Content-type", KakaoLoginInfo.ContentType); // 말그대로 헤더에 담는 값

		// httpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", KakaoLoginInfo.grant_type);
		params.add("client_id", KakaoLoginInfo.client_id);
		params.add("redirect_uri", redirect_uri);
		// 방금 받은 코드임
		params.add("code", code);
		
		// body data와 header값을 가지고있는 하나의 httpEntity가 된다
		// body값 header값을 둘다 하나로 만든다
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(KakaoLoginInfo.OauthToken_request, HttpMethod.POST,
				kakaoTokenRequest, String.class);
		
		return response;
	}

	@Override
	public OAuthToken ObjectOauth(ResponseEntity<String> body) {
		ObjectMapper objectMapper = new ObjectMapper();
		// 여기다가 json 으로 담아낼 예정
		OAuthToken oauthToken = null;

		try {
			// getter,setter가 없거나, 변수이름을 정확하게 기입하지않으면 틀린다
			oauthToken = objectMapper.readValue(body.getBody(), OAuthToken.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return oauthToken;
	}

	@Override
	public ResponseEntity<String> responseUserInfo(OAuthToken oauthToken) {

		RestTemplate rt2 = new RestTemplate();

		// httpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		// 현재 http형식이 key-value 형식임을 알린다
		headers2.add("Content-type", KakaoLoginInfo.ContentType); // 말그대로 헤더에 담는 값

		// body data와 header값을 가지고있는 하나의 httpEntity가 된다
		// body값 header값을 둘다 하나로 만든다
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt2.exchange(KakaoLoginInfo.UserInfoSelect, HttpMethod.POST,
				kakaoProfileRequest2, String.class);
		
		
		// 회원 정보까지 조회하는게 response2.getbody()
		
		return response;
	}

	@Override
	public KakaoProfile kakaoProfile(ResponseEntity<String> response) {

		ObjectMapper objectMapper2 = new ObjectMapper();
		// 여기다가 json 으로 담아낼 예정
		KakaoProfile kakaoProfile = null;

		try {
			// getter,setter가 없거나, 변수이름을 정확하게 기입하지않으면 틀린다
			kakaoProfile = objectMapper2.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 카카오 프로파일에 모든정보를 매퍼로 담는과정이다

		// 정보를 받는것을 확인
		System.out.println(" 카카오 아이디 (번호)" + kakaoProfile.getId());
		System.out.println(" 카카오 이메일 (String)" + kakaoProfile.getKakao_account().getEmail());
		System.out.println(" 카카오 닉네임 (String)" + kakaoProfile.getProperties().getNickname());
		System.out.println(" 카카오 연령대 (String)" + kakaoProfile.getKakao_account().getAge_range());

		return kakaoProfile;
	}

	@Override
	public KakaoProfile intergration(String code, String redirect_uri) {
		// responeKEntity : 인증코드를 받아내어 카카오측에 전송
		// 카카오 측에서 보내준 OAUTHTOKEN을 클래스 오브젝트화 시켜서 받아내는 부분이다
		// Oauth액세스 토큰
		OAuthToken oauthToken = ObjectOauth(responeKEntity(code, redirect_uri));

		// 인증받은 후 oauthResponse란 해당 회원정보를 모두 body데이터에 json형태로 담아낸 값이다
		ResponseEntity<String> oauthResponse = responseUserInfo(oauthToken);

		KakaoProfile kakaoProfile = kakaoProfile(oauthResponse);

		return kakaoProfile;
	}

}
