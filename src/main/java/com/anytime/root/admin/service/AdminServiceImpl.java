package com.anytime.root.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anytime.root.user.dao.UserDAO;
import com.anytime.root.user.dto.UserDTO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	UserDAO userMapper;

	@Override
	public ArrayList<UserDTO> AllUserList() {
		ArrayList<UserDTO> AllUserList = new ArrayList<UserDTO>();
		AllUserList = userMapper.AllUserList();
		
		
		
		return AllUserList;
	}

	@Override
	public int ChangePermissions(String Permissions, String email) {
		if(Permissions.equals( "true")) {
			userMapper.changePermissionsFalse(email);
		}else {
			userMapper.changePermissionsTure(email);
		}
		
		return 0;
	}

	
	
	
	
	
	
}
