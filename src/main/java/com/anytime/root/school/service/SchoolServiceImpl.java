package com.anytime.root.school.service;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anytime.root.user.dto.OAuthToken;
import com.anytime.root.user.dto.School;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SchoolServiceImpl implements SchoolService{
	private String appkey = "3240a3901b20e0d9dfd348eba97ede9c";
	private String requestUrl = "http://www.career.go.kr/cnet/openapi/getOpenApi"
			+ "?apiKey="+appkey+"&svcType=api&svcCode=SCHOOL&contentType=json"
			+ "&gubun=high_list&searchSchulNm=";
	
	@Override
	public ResponseEntity<String> responeSchoolEntity(String schoolName){
		
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<String> response =null;
		
		// 쿼리스트링이 있는것을 보면 get 방식
		try {
			response = rt.exchange(requestUrl+schoolName, HttpMethod.GET, null , String.class);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public School getSchool(ResponseEntity<String> response) {
		ObjectMapper objectMapper = new ObjectMapper();
		School schoolObject = null;
		
		// 널포인트 에러 
		try {
			// getter,setter가 없거나, 변수이름을 정확하게 기입하지않으면 틀린다
			schoolObject = objectMapper.readValue(response.getBody(), School.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return schoolObject;
		
	}
	
	
	
}
