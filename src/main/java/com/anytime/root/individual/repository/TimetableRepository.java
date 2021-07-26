package com.anytime.root.individual.repository;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.individual.dto.Timetable;

public interface TimetableRepository {
	public int checkUser(String id);
	public void makeSetDefault(String id);
	public ArrayList<Timetable> getTimetable(@Param("semester") String semester, @Param("id") String id);
	public int countById(String id);
	public int getItemNo(String id);
	public void add(Timetable timetable);
	public void modify(Timetable timetable);
	public void delete(@Param("id") String id, @Param("itemNo") int itemNo);
	public String getMainTimetable(String id);
	public Map<String, Object> getSetting(@Param("semester") String semester, @Param("id") String id);
	public void setMain(@Param("main") String main, @Param("id") String id);
	public void setScope(@Param("semester") String semester, @Param("scope") String scope, @Param("id") String id);
	public void reset(@Param("semester") String semester, @Param("id") String id);
}
