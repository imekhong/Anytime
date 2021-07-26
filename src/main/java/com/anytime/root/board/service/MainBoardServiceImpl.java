package com.anytime.root.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.anytime.root.board.repository.MainBoardRepository;

@Service
public class MainBoardServiceImpl implements MainBoardService{
	
	private final MainBoardRepository boardRepository;
	@Autowired
	public MainBoardServiceImpl(MainBoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Override
	public void getMain(Model model, String id, String school) {
		model.addAttribute("cbrList", boardRepository.getCmnBoardByDate());
		model.addAttribute("cblList", boardRepository.getCmnBoardByLike());
		model.addAttribute("sbsrList", boardRepository.getSchoolBoardByDate("s", school));
		model.addAttribute("sbcrList", boardRepository.getSchoolBoardByDate("c", school));
		model.addAttribute("sbirList", boardRepository.getSchoolBoardByDate("i", school));
		model.addAttribute("sbfrList", boardRepository.getSchoolBoardByDate("f", school));
		model.addAttribute("sblList", boardRepository.getSchoolBoardByLike(school));
	}

	@Override
	public void getMycBoard(Model model, int page, String id) {
		int pageSize = 10;
		int allCount = boardRepository.cmnListCount(id);
		int pageCount = allCount/pageSize;
		if(allCount%pageSize != 0 || pageCount == 0) {
			pageCount += 1;
		}
		model.addAttribute("pageCount", pageCount);
		int end = page * pageSize;
		int start = end + 1 - pageSize;
		model.addAttribute("boardList", boardRepository.getMyCmnBoard(id, start, end));

	}
	
	@Override
	public void getMysBoard(Model model, int page, String id) {
		int pageSize = 10;
		int allCount = boardRepository.schoolListCount(id);
		int pageCount = allCount/pageSize;
		if(allCount%pageSize != 0 || pageCount == 0) {
			pageCount += 1;
		}
		model.addAttribute("pageCount", pageCount);
		int end = page * pageSize;
		int start = end + 1 - pageSize;
		model.addAttribute("boardList", boardRepository.getMySchoolBoard(id, start, end));
	}

	@Override
	public void getMycReply(Model model, String id) {
		model.addAttribute("replyList", boardRepository.getMycreplyList(id));
	}

	@Override
	public void getMysReply(Model model, String id) {
		model.addAttribute("replyList", boardRepository.getMysreplyList(id));
	}

	@Override
	public String getSection(int postNo) {
		return boardRepository.getSection(postNo);
	}
}
