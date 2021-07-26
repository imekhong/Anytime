package com.anytime.root.individual.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anytime.root.common.session.SessionName;
import com.anytime.root.individual.dto.Score;
import com.anytime.root.individual.service.ScoreService;

@Controller
public class ScoreController {
	
	private final ScoreService scoreService;
	@Autowired
	public ScoreController(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
	
	@RequestMapping("score")
	public String goScore() {
		return "redirect:/score/sem1/all";
	}
	
	@RequestMapping("score/{semester}/{selc}")
	public String score(Model model, HttpSession session, 
			@PathVariable String semester, @PathVariable String selc) {
		String id = (String)session.getAttribute(SessionName.ID);
		scoreService.getScore(model, id, semester, selc);
		return "score/score";
	}
	
	@PostMapping(value="score/delete", produces = "application/json; charset=utf8")
	@ResponseBody
	public void delete(Score score ) {
		scoreService.deleteByItemNo(score);
	}
	
	@PostMapping(value="score/save", produces = "application/json; charset=utf8")
	@ResponseBody
	public void save(@RequestParam String id, @RequestParam String semester,
			@RequestParam(value="arr" ,required=false) String arr) {
		scoreService.save(id, semester, arr);
	}
	
	@PostMapping(value="score/reset", produces = "application/json; charset=utf8")
	@ResponseBody
	public void reset(@RequestParam String id,
			@RequestParam String semester, @RequestParam String selc) {
		scoreService.reset(id, semester, selc);
	}
}
