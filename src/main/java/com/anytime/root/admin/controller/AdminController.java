package com.anytime.root.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anytime.root.admin.service.AdminService;
import com.anytime.root.script.ScriptUtils;
import com.anytime.root.user.dto.UserDTO;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	
	@GetMapping(value = "MemberManagement")
	public String MemberManagement(Model model) {
		ArrayList<UserDTO>dto = new ArrayList<UserDTO>();  
		dto = as.AllUserList();
		
		model.addAttribute("AlluserList",dto);
		
		
		return "admin/UserListView";
	}
	
	@GetMapping(value = "changePermissions/{email}/{permissions}")
	public void changePermissions(Model model, @PathVariable("permissions")String permissions,
			@PathVariable("email")String email,HttpServletResponse response) throws IOException {
		System.out.println(permissions + " ::: "+email);
		as.ChangePermissions(permissions, email);
		
		
		ScriptUtils.alertAndMovePage(response, "변경 완료했습니다.", "/root/MemberManagement");
	}
	
	
	
	
	
	
	
}
