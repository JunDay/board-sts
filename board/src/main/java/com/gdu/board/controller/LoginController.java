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
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member member) {
		Member loginMember = memberService.getMemberLogin(member);
		
		// 유효성 검사
		// 실패
		if(loginMember == null) {
			return "redirect:/login";
		}
		// 성공, 세션 생성 (옳은 방법은 아님, 하지만 실무에서 자주 사용하는 방법)
		session.setAttribute("loginMember", loginMember);
		return "redirect:/boardList";
	}
	
	@GetMapping("/logut")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/boardList";	//IndexController, index.jsp 등이 필요
	}
}
