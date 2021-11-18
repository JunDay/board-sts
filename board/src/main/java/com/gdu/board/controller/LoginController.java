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
	
/* 로그인 */
	@GetMapping("/login")
	public String login() {
		log.debug("[Debug] \"START\" LoginController.login() | Get()");
		return "login";
	}
	@PostMapping("/login")
	public String login(HttpSession session, Member member) {
		log.debug("[Debug] \"START\" LoginController.login() | Post()");
		
		// 1. 로그인할 Member 정보 조회
		Member loginMember = memberService.getMemberLogin(member);
		
		// 2. 로그인 정보 확인
		if(loginMember == null) {
			log.warn("[WARN] \"FAILED\" login");
			return "redirect:/login";
		}
		
		// 3. 로그인 세션 생성
		session.setAttribute("loginMember", loginMember);
		log.debug("[Debug] \"SUCCESS\" login session : "+loginMember);
		
		return "redirect:/boardList";
	}
	
/* 로그아웃 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.debug("[Debug] \"START\" LoginController.logout() | Get()");
		
		// 1. 세션 해제
		session.invalidate();
		
		return "redirect:/boardList";
	}
}
