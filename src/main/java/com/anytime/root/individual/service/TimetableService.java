package com.anytime.root.individual.service;

import org.springframework.ui.Model;

import com.anytime.root.individual.dto.Timetable;

public interface TimetableService {
	public String getMainTimetable(String id);
	public void getTimetable(Model model, String semester, String id);
	public void addTimetable(Timetable timetable);
	public void modifyTimetable(Timetable timetable);
	public void deleteTimetable(String id, int itemNo);
	public void settingTimetable(String id, String scope, String main, String semester);
	public void resetTimetable(String id, String semester);
}
