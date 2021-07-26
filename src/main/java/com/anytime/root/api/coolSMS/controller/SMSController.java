package com.anytime.root.api.coolSMS.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.api.coolSMS.service.SMSService;
import com.google.gson.JsonObject;

@Controller
public class SMSController {
	private SMSService smsService;
	
	@Autowired
	public SMSController(SMSService smsService) {
		this.smsService = smsService;
	}
	
	@PostMapping(value="member/auth/sendsms", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String sendSMS(@RequestParam("num")String userNumber) {
		JsonObject jsonObject = new JsonObject();
		String code = smsService.sendSMS(userNumber);
		jsonObject.addProperty("code", code);
		return jsonObject.toString();
	}
	
	@GetMapping(value="member/auth/sms")
	@ResponseBody
	public void authSMS(HttpSession session) {
		session.setAttribute("smsauth", "smsauth");
	}
}
