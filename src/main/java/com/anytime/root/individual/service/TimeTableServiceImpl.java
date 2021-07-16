package com.anytime.root.individual.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.anytime.root.individual.dto.Timetable;
import com.anytime.root.individual.repository.TimetableRepository;

import net.sf.json.JSONArray;

@Service
public class TimeTableServiceImpl implements TimetableService{
	
	private final TimetableRepository timetableRepository;
	@Autowired
	public TimeTableServiceImpl(TimetableRepository timetableRepository) {
		this.timetableRepository = timetableRepository;
	}
	
	@Override
	public String getMainTimetable(String id) {
		return timetableRepository.getMainTimetable(id);
	}

	@Override
	public void getTimetable(Model model, String semester, String id) {
		if(timetableRepository.checkUser(id) != 1) {
			timetableRepository.makeSetDefault(id);
		}
		
		List<Timetable> list = timetableRepository.getTimetable(semester, id);
		//JSONArray jsonArray = new JSONArray();
		model.addAttribute("timetableList", JSONArray.fromObject(list));
		Map<String, Object> map = timetableRepository.getSetting(semester, id);
		//현재 시간표가 기본페이지 인지 true, false
		model.addAttribute("setMain", map.get("MAIN").toString().equals(semester));
		//key값이 대문자이므로 현재 시간표 semester를 대문자로 변환하여 value 얻어옴
		model.addAttribute("scope", map.get(semester.toUpperCase()));
	}
	
	@Override
	public void addTimetable(Timetable timetable) {
		int count = timetableRepository.countById(timetable.getId());
		if( count > 0) {
			timetable.setItemNo(timetableRepository.getItemNo(timetable.getId())+1);
		}else {
			timetable.setItemNo(1);
		}
		timetableRepository.add(timetable);
	}
	
	@Override
	public void modifyTimetable(Timetable timetable) {
		timetableRepository.modify(timetable);
	}

	@Override
	public void deleteTimetable(String id, int itemNo) {
		timetableRepository.delete(id, itemNo);
	}

	@Override
	public void settingTimetable(String id, String scope, String main, String semester) {
		String oriMain = timetableRepository.getMainTimetable(id);
		if(main.equals("true")) {
			timetableRepository.setMain(semester, id);
		}else {
			if(oriMain.equals(semester)) {
				timetableRepository.setMain("nan", id);
			}
		}
		timetableRepository.setScope(semester, scope, id);
	}

	@Override
	public void resetTimetable(String id, String semester) {
		timetableRepository.reset(semester, id);
	}
}
