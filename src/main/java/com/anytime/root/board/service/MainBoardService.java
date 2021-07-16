package com.anytime.root.board.service;

import org.springframework.ui.Model;

public interface MainBoardService {
	public void getMain(Model model, String id, String school);
	public void getMycBoard(Model model, int page, String id);
	public void getMysBoard(Model model, int page, String id);
	public void getMycReply(Model model, String id);
	public void getMysReply(Model model, String id);
	public String getSection(int postNo);
}
