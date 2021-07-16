package com.anytime.root.join.service;

import java.util.Map;

public interface JoinService {
	public int insertUser(Map<String, Object> user); // 말 그대로 회원가입 과정
	public String randomPW();
}
