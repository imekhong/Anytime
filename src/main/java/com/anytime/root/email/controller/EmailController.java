package com.anytime.root.email.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anytime.root.email.staticAuthKey;
import com.anytime.root.email.service.MailSendService;

@Controller
public class EmailController {
	 @Autowired
	 private MailSendService mss;
	
	
	@PostMapping(value = "member/signUp", produces = "application/json; charset=utf-8")
	@ResponseBody
    public String signUp(@RequestBody Map<String, Object> map){
       // DB에 기본정보 insert
		System.out.println(map.get("generalEmail"));

       //임의의 authKey 생성 & 이메일 발송
       mss.sendAuthMail((String)map.get("generalEmail"));
       
       
       //결국 ajax통신
     return "{\"test\" : true }";

 	}
	
	
	
	
	@GetMapping("signUpConfirm")
	 public String signUpConfirm(@RequestParam Map<String, String> map,Model model){
	    //email, authKey 가 일치할경우 authStatus 업데이트
		System.out.println(map.get("authKey"));
	    if(staticAuthKey.authKey.equals(map.get("authKey")) ) {
	    	System.out.println("인증완료");
	    	model.addAttribute("UserJoinAuth", "general");
	    	model.addAttribute("emailauth", "emailauth");
	    	model.addAttribute("OKemail", map.get("email"));
	    }
		
	    
	    
	    return "join/joinView";
	}
	
}
