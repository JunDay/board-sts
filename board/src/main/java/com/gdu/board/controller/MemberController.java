package com.gdu.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.board.service.MemberService;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	private final int ROW_PER_PAGE = 10;
	
	@GetMapping("/admin/memberList")
	public String memberList(Model model, 
			@RequestParam(defaultValue = "1") int currentPage, 
			@RequestParam(required = false) String memberId) {
		Map<String, Object> map = memberService.selectMemberListByMemberId(memberId, currentPage, ROW_PER_PAGE);
		
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("memberId", memberId);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		return "admin/memberList";
	}
	
	@GetMapping("/joinMember")
	public String joinMember() {
		return "joinMember";
	}
	@PostMapping("joinMember")
	public String joinMember(Member member) {
		int result = memberService.getInsertMember(member);
		if(result == 0 ) {
			log.debug("회원가입 실패");
		}
		log.debug("회원가입 성공");
		
		return "redirect:/boardList";
	}
}
