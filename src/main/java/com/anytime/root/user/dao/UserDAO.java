package com.anytime.root.user.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.user.dto.UserDTO;

public interface UserDAO {
	public void insertUser(UserDTO dto);
	public UserDTO loginUser(String email);
	public ArrayList<UserDTO> AllUserList();
	public void changePermissionsTure(@Param("email") String email);	
	public void changePermissionsFalse(@Param("email") String email);
	public UserDTO SelectUserInfoCheck(String email);
	
}
