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
	
/* Member PW 변경 */
	@GetMapping("/member/updateMemberPassword")
	public String updateMemberPassword(Model model, int memberNo) {
		log.debug("[Debug] \"START\" MemberController.updateMemberPassword() | Get()");
		
		// 1. member 정보 조회
		Member member = memberService.getSelectMemberOne(memberNo);
		
		// 2. 조회된 member 정보 전달
		model.addAttribute("member", member);
		
		return "member/updateMemberPassword";
	}
	@PostMapping("/member/updateMemberPassword")
	public String updateMemberPassword(Member member, String newMemberPw, String newMemberPwCheck) {
		log.debug("[Debug] \"START\" MemberController.updateMemberPassword() | Post()");
		
		// 1. 기존 PW 확인
		if(memberService.getSelectMemberPwCheck(member) != 1) {
			log.error("[ERROR] \"FAILED\" update member password | 입력된 기존 PW 불일치 ");
			return "redirect:/member/memberOne?memberNo="+member.getMemberNo();
		}
		
		// 2. 새로운 PW 확인
		if(!newMemberPw.equals(newMemberPwCheck)) {
			log.error("[ERROR] \"FAILED\" update member password | 입력된 새로운 PW 불일치 ");
			return "redirect:/member/memberOne?memberNo="+member.getMemberNo();
		}
		
		// 3. member PW 변경
		memberService.getUpdateMemberPassword(member, newMemberPw);
		
		return "redirect:/member/memberOne?memberNo="+member.getMemberNo();
	}

/* Member 삭제 */
	@GetMapping("/member/deleteMember")
	public String deleteMember(Model model, int memberNo) {
		log.debug("[Debug] \"START\" MemberController.deleteMember() | Get()");
		
		// 1. Post에 넘겨줄 memberNo 설정
		model.addAttribute("memberNo", memberNo);
		
		return "member/deleteMember";
	}
	@PostMapping("/member/deleteMember")
	public String delteMember(HttpSession session, Member member) {
		log.debug("[Debug] \"START\" MemberController.deleteMember() | Post()");
		
		// 1. 로그인된 정보와 입력된 정보를 이용해 삭제
		memberService.getDeleteMember(session, member);
		return "redirect:/boardList";
	}
	
/* Member 정보 변경 */
	@GetMapping("/member/updateMember")
	public String updateMember(Model model, int memberNo) {
		log.debug("[Debug] \"START\" MemberController.updateMember() | Get()");
		
		// 1. 표시해줄 Member 정보 조회
		Member member = memberService.getSelectMemberOne(memberNo);
		
		// 2. Member 정보 전달
		model.addAttribute("member", member);
		
		return "member/updateMember";
	}
	@PostMapping("/member/updateMember")
	public String updateMember(Member member) {
		log.debug("[Debug] \"START\" MemberController.updateMember() | Post()");
		
		// 1. Member 정보 업데이트
		int row =  memberService.getUpdateMember(member);
		if(row == 0) {
			log.warn("[WARN] \"FAILED\" update member");
			return "admin/memberOne?memberNo="+member.getMemberNo();
		}
		
		log.debug("[Debug] \"SUCCESS\" member update");
		return "redirect:/member/memberOne?memberNo="+member.getMemberNo();
	}
	
/* Member 정보 조회 */
	@GetMapping("/member/memberOne")
	public String memberOne(Model model, int memberNo) {
		log.debug("[Debug] \"START\" MemberController.memberOne() | Get()");
		
		// 1. Member 정보 조회
		Member member = memberService.getSelectMemberOne(memberNo);
		
		// 2. Member 정보 전달
		model.addAttribute("member", member);
		
		return "member/memberOne";
	}
	
/* Member 회원가입 */
	@GetMapping("/joinMember")
	public String joinMember() {
		log.debug("[Debug] \"START\" MemberController.joinMember() | Get()");
		return "joinMember";
	}
	@PostMapping("joinMember")
	public String joinMember(Member member) {
		log.debug("[Debug] \"START\" MemberController.joinMember() | Post()");
		log.debug(" ├[param] member : "+member.getMemberId());
		
		// 1. ID 또는 PW가 누락 시 
		if((member.getMemberId() == null) || (member.getMemberPw() == null)) {
			log.warn("[WARN] \"FAILED\" join member | memberId 또는 memberPw 누락");
			return "redirect:/joinMember";
		}
		
		// 2. Member 삽입
		int row = memberService.getInsertMember(member);
		if(row == 0 ) {
			log.error("[ERROR] \"FAILED\" join member | 삽입 실패");
			return "redirect:/joinMember";
		}
		log.debug("[Debug] \"SUCCESS\" join member");
		
		return "redirect:/boardList";
	}
}
