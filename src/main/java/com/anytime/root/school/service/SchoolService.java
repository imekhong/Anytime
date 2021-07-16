package com.anytime.root.school.service;

import org.springframework.http.ResponseEntity;

import com.anytime.root.user.dto.School;

public interface SchoolService {
	public ResponseEntity<String> responeSchoolEntity(String schoolName);
	public School getSchool(ResponseEntity<String> response);
	
}
