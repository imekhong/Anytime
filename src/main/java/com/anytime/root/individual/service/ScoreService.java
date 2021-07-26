package com.anytime.root.individual.service;

import org.springframework.ui.Model;

import com.anytime.root.individual.dto.Score;

public interface ScoreService {
	public void getScore(Model model, String id, String semester, String selc);
	public void deleteByItemNo(Score score);
	public void save(String id, String semester, String arr);
	public void reset(String id, String semester, String selc);
}
