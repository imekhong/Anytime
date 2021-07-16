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
import com.anytime.root.individual.dto.Timetable;
import com.anytime.root.individual.service.TimetableService;

@Controller
public class TimetableController {
	
	private final TimetableService timetableService;
	@Autowired
	public TimetableController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	@RequestMapping("timetable")
	public String timetableMain(HttpSession session) {
		String id = (String)session.getAttribute(SessionName.ID);
		String semester = timetableService.getMainTimetable(id);
		if(semester == null) return "redirect:/timetable/sem1";
		else return "redirect:/timetable/"+semester;
	}
	
	@RequestMapping("timetable/{semester}")
	public String timetable(Model model, HttpSession session,
			@PathVariable String semester) {
		if(semester.equals("nan")) return "timetable/timetable_nan";
		String id = (String)session.getAttribute(SessionName.ID);
		timetableService.getTimetable(model, semester, id);
		return "timetable/timetable";
	}
	
	@PostMapping(value="timetable/add", produces = "application/json; charset=utf8")
	@ResponseBody
	public void addTimetable(Timetable timetable) {
		timetableService.addTimetable(timetable);
	}
	
	@PostMapping(value="timetable/modify", produces = "application/json; charset=utf8")
	@ResponseBody
	public void modify(Timetable timetable) {
		timetableService.modifyTimetable(timetable);
	}
	
	@PostMapping(value="timetable/delete/{id}/{itemNo}", produces = "application/json; charset=utf8")
	@ResponseBody
	public void deleteTimetable(@PathVariable String id, @PathVariable int itemNo) {
		timetableService.deleteTimetable(id, itemNo);
	}
	
	@PostMapping(value="timetable/setting", produces = "application/json; charset=utf8")
	@ResponseBody
	public void setting(@RequestParam String id, @RequestParam String scope,
			@RequestParam String main, @RequestParam String semester) {
		timetableService.settingTimetable(id, scope, main, semester);
	}
	
	@PostMapping(value="timetable/reset", produces = "application/json; charset=utf8")
	@ResponseBody
	public void reset(@RequestParam String id, @RequestParam String semester) {
		timetableService.resetTimetable(id, semester);
	}
	
}
