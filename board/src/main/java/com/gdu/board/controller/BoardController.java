package com.gdu.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.board.service.BoardService;
import com.gdu.board.service.CategoryService;
import com.gdu.board.vo.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	CategoryService categoryService;
	
	private final int ROW_PER_PAGE = 10;
	
	/* DELETE Board */
	@GetMapping("/deleteBoard")
	public String deleteBoard(int boardNo) {
		boardService.deleteBoard(boardNo);
		
		return "redirect:/boardList";
	}
	
	/* INSERT Board */
	@GetMapping("/addBoard")
	public String addBoard(Model model) {
		List<Category> categoryList = categoryService.selectCategoryList();
		model.addAttribute(categoryList);
		return "member/addBoard";
	}
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		boardService.addBoard(board);
		log.debug(board.toString());
		return "redirect:/boardOne?boardNo="+board.getBoardNo();
	}
	
	/* SELECT 1:Board, N:Comment */
	@GetMapping("/boardOne")
	public String boardOne(Model model, int boardNo) {
		Board board = boardService.getBoardOneAndComments(boardNo);
		model.addAttribute(board);
		return "boardOne";
	}
	
	/* SELECT N:Board BY boardCategory */
	@GetMapping("/boardList")
	// currentPage가 넘어오지 않으면 1이 들어간다.
	public String boardList(Model model, 
			@RequestParam(defaultValue = "1") int currentPage, 
			@RequestParam(required = false) String boardCategory) {
		
			Map<String, Object> map = boardService.getBoardListByCategory(boardCategory, currentPage, ROW_PER_PAGE);
			List<Category> categoryList = categoryService.selectCategoryList();
			
			model.addAttribute("boardList", map.get("boardList"));
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("lastPage", map.get("lastPage"));
			model.addAttribute("currentPage", currentPage);
			
		return "boardList";
	}
}
