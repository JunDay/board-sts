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

/* Category 삭제 */
	@GetMapping("/admin/deleteCategory")
	public String deleteCategory(HttpSession session, Model model, String categoryName) {
		log.debug("[Debug] \"START\" CategoryController.deleteCategory() | Get()");
		log.debug(" ├[param] categoryName : "+categoryName);
		
		// 0. 관리자 ID 표시하기 위한 세션 정보
		Member loginMember = (Member)session.getAttribute("loginMember");
		log.debug(" ├[param] loginMember.memberId : "+loginMember.getMemberId());
		
		// 1. 카테고리 리스트 조회
		Map<String, Object> map = boardService.getBoardListByCategory(categoryName, 1, 10);
		
		// 2. 총 게시글 수 조회
		int totalBoardCount = boardService.selectTotalBoardCountByCategory(categoryName);
		log.debug(" ├[param] totalBoardCount : "+totalBoardCount);		
		
		// 3. 조회한 정보들 전달
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("boardList", map.get("boardList"));
		model.addAttribute("totalBoardCount", totalBoardCount);
		
		return "admin/deleteCategory";
	}
	@PostMapping("/admin/deleteCategory")
	public String deleteCategory(HttpSession session, Member member, String categoryName) {
		log.debug("[Debug] \"START\" CategoryController.deleteCategory() | Post()");
		log.debug(" ├[param] categoryName : "+categoryName);
		log.debug(" ├[param] member.memberId : "+member.getMemberId());
		
		// 1. 로그인된 Admin 정보
		Member loginMember = (Member)session.getAttribute("loginMember");
		log.debug(" ├[param] loginMember.memberId : "+loginMember.getMemberId());
		
		// 2. 입력된 ID와 PW 확인
		if(memberService.getSelectMemberPwCheck(member) == 0 && loginMember.getMemberId() != member.getMemberId()) {
			log.warn("[WARN] \"FAILED\" delete category | memberId 또는 memberPw 오기입");
			return "redirect:/admin/categoryList";
		}
		
		// 3. Category 삭제
		categoryService.deleteCategory(categoryName);
		
		return "redirect:/admin/categoryList";
	}
	
/* Category 추가 */
	@GetMapping("/admin/addCategory")
	public String addCategory() {
		log.debug("[Debug] \"START\" CategoryController.addCategory() | Get()");
		
		return "admin/addCategory";
	}
	@PostMapping("/admin/addCategory")
	public String addCategory(Category category) {
		log.debug("[Debug] \"START\" CategoryController.addCategory() | Post()");
		log.debug(" ├[param] category : "+category.toString());
		
		// 1. Category 삽입
		categoryService.insertCategory(category);
		
		return "redirect:/admin/categoryList";
	}
	
/* Category 목록 조회 */
	@GetMapping("/admin/categoryList")
	public String categoryList(Model model) {
		log.debug("[Debug] \"START\" CategoryController.categoryList() | Get()");
		
		// 1. Categoet 목록 데이터 가공
		List<Category> categoryList = categoryService.selectCategoryList();
		
		// 2. Category 목록 정보 전달
		model.addAttribute(categoryList);
		
		return "admin/categoryList";
	}
}
