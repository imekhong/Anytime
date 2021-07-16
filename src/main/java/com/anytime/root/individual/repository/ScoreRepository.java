package com.anytime.root.individual.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.anytime.root.individual.dto.Score;

public interface ScoreRepository {
	public int countById(String id);
	public int getItemNo(String id);
	public ArrayList<Score> getScore(@Param("id") String id, @Param("semester")String semester, @Param("selc")String selc);
	public void deleteByItemNo(Score score);
	public void modify(Score score);
	public void add(Score score);
	public void reset(@Param("id") String id, @Param("semester")String semester, @Param("selc")String selc);
	public ArrayList<String> getSubjectList(@Param("id") String id, @Param("semester")String semester);
	public List<Map<String, Object>> getForChart(@Param("id")String id, @Param("semester")String semester, @Param("subject_list")ArrayList subject_list);
	public ArrayList<Integer> getScoreForChart(@Param("id")String id, @Param("semester")String semester, @Param("type")String type);
}
