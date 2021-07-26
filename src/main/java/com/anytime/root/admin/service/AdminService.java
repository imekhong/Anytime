package com.anytime.root.admin.service;

import java.util.ArrayList;

import com.anytime.root.user.dto.UserDTO;

public interface AdminService {
	public ArrayList<UserDTO> AllUserList();
	public int ChangePermissions(String Permissions , String email);

}
