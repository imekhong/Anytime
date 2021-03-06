package com.anytime.root.api.coolSMS.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class SMSServiceImpl implements SMSService{
	private String API_KEY = "API_KEY 입력";
	private String API_SECRET = "API_SECRET 입력";
	private String FROM = "발신번호 입력";
	
	@Override
	public String sendSMS(String userNumber) {
		System.out.println("send sms");
		int authNo = (int)(Math.random() * (99999 - 10000 + 1)) + 10000;
		String text = "애니타임 인증번호: ["+authNo+"]";
		Message coolsms = new Message(API_KEY, API_SECRET);
		
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", userNumber);
		set.put("from", FROM);
		set.put("type", "sms");
		set.put("text", text);

		try {
			JSONObject obj = (JSONObject) coolsms.send(set);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
		return ""+authNo;		
	}
}
