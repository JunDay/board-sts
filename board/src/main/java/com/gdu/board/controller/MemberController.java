package com.gdu.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.board.service.MemberService;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
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
