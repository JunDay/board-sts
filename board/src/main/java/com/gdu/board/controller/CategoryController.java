package com.gdu.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.board.service.BoardService;
import com.gdu.board.service.CategoryService;
import com.gdu.board.service.MemberService;
import com.gdu.board.vo.Board;
import com.gdu.board.vo.Category;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	MemberService memberService;
	@Autowired
	BoardService boardService;
	
	@GetMapping("/admin/deleteCategory")
	public String deleteCategory(HttpSession session, Model model, String categoryName) {
		log.debug("[Debug] \"START\" CategoryController.deleteCategory()");
		log.debug(" ├[param] categoryName : "+categoryName);
		
		// 0. 관리자 ID 표시하기 위한 세션 정보
		Member loginMember = (Member)session.getAttribute("loginMember");
		log.debug(" ├[param] loginMember.memberId : "+loginMember.getMemberId());
		
		// 1. 카테고리 리스트 조회
		Map<String, Object> map = boardService.getBoardListByCategory(categoryName, 1, 10);
		
		// 2. 총 게시글 수 조회
		int totalBoardCount = boardService.selectTotalBoardCountByCategory(categoryName);
		log.debug(" ├[param] totalBoardCount : "+totalBoardCount);		
		
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("boardList", map.get("boardList"));
		model.addAttribute("totalBoardCount", totalBoardCount);
		return "admin/deleteCategory";
	}
	@PostMapping("/admin/deleteCategory")
	public String deleteCategory(HttpSession session, Member member, String categoryName) {
		log.debug("[Debug] \"START\" CategoryController.deleteCategory()");
		log.debug(" ├[param] categoryName : "+categoryName);
		log.debug(" ├[param] member.memberId : "+member.getMemberId());
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		log.debug(" ├[param] loginMember.memberId : "+loginMember.getMemberId());
		// 1. 입력한 ID와 PW가 같지 않으면 이전으로
		if(memberService.selectMemberPwCheck(member) == 0 && loginMember.getMemberId() != member.getMemberId()) {
			log.debug(" ├[ERROR] memberId or memberPw NOT matched");
			return "redirect:/admin/categoryList";
		}
		categoryService.deleteCategory(categoryName);
		return "redirect:/admin/categoryList";
	}
	
	@GetMapping("/admin/addCategory")
	public String addCategory() {
		return "admin/addCategory";
	}
	@PostMapping("/admin/addCategory")
	public String addCategory(Category category) {
		categoryService.insertCategory(category);
		return "redirect:/admin/categoryList";
	}
	
	@GetMapping("/admin/categoryList")
	public String categoryList(Model model) {
		List<Category> categoryList = categoryService.selectCategoryList();
		model.addAttribute(categoryList);
		return "admin/categoryList";
	}
}
