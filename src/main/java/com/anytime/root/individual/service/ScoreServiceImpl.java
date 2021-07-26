package com.anytime.root.individual.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.anytime.root.individual.dto.Score;
import com.anytime.root.individual.repository.ScoreRepository;

import net.sf.json.JSONArray;

@Service
public class ScoreServiceImpl implements ScoreService{

	private final ScoreRepository scoreRepository;
	@Autowired
	public ScoreServiceImpl(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}
	
	@Override
	public void getScore(Model model, String id, String semester, String selc) {
		List<Score> list = scoreRepository.getScore(id, semester, selc);
		model.addAttribute("scoreList", JSONArray.fromObject(list));
		ArrayList<String> subject_list = scoreRepository.getSubjectList(id,semester);
		if(subject_list.size() > 0) {
			model.addAttribute("subjectList", JSONArray.fromObject(subject_list));
			model.addAttribute("mid", JSONArray.fromObject(scoreRepository.getScoreForChart(id, semester, "middle")));
			model.addAttribute("fin", JSONArray.fromObject(scoreRepository.getScoreForChart(id, semester, "final")));
			model.addAttribute("mar", JSONArray.fromObject(scoreRepository.getScoreForChart(id, semester, "march")));
			model.addAttribute("jun", JSONArray.fromObject(scoreRepository.getScoreForChart(id, semester, "june")));
			model.addAttribute("sep", JSONArray.fromObject(scoreRepository.getScoreForChart(id, semester, "september")));
		}
	}
	
	@Override
	public void deleteByItemNo(Score score) {
		scoreRepository.deleteByItemNo(score);
	}
	
	@Override
	public void save(String id, String semester, String arr) {
		List<Map<String, Object>> list = new ArrayList<>();
		Score score = new Score();
		score.setId(id);
		score.setSemester(semester);
		list = JSONArray.fromObject(arr);
		for(Map<String, Object> map:list) {
			score.setType((String)map.get("type"));
			score.setSubject((String)map.get("subject"));
			score.setScore(Integer.parseInt((String)map.get("score")));
			int item = (int)map.get("itemNo");
			if(item == -1) {
				int count = scoreRepository.countById(id);
				if(count > 0) {
					score.setItemNo(scoreRepository.getItemNo(id)+1);
				}else {
					score.setItemNo(1);
				}
				scoreRepository.add(score);
			}else {
				score.setItemNo(item);
				scoreRepository.modify(score);
			}
		}
	}

	@Override
	public void reset(String id, String semester, String selc) {
		scoreRepository.reset(id, semester, selc);
	}
	
}
