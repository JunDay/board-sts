package com.gdu.board.controller;

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
public class AdminController {
	@Autowired
	MemberService memberService;
	
	// Member 목록 페이징 상수
	private final int ROW_PER_PAGE = 10;
	
/* Member 강제 탈퇴 */
	@GetMapping("/admin/deleteMember")
	public String deleteMember(Model model, int memberNo) {
		log.debug("[Debug] \"START\" AdminController.deleteMember() | Get()");
		log.debug(" ├[param] memberNo : "+memberNo);
		
		// 1. memberNo 전달
		model.addAttribute("memberNo", memberNo);
		
		return "admin/deleteMember";
	}
	@PostMapping("/admin/deleteMember")
	public String delteMember(HttpSession session, Member member) {
		log.debug("[Debug] \"START\" AdminController.deleteMember() | Post()");
		
		// 1. 현재 로그인 정보와 삭제할 Member 정보 전달
		memberService.getDeleteMember(session, member);
		
		return "redirect:/admin/memberList";
	}
	
/* Member 정보 변경 */
	@GetMapping("/admin/updateMember")
	public String updateMember(Model model, int memberNo) {
		log.debug("[Debug] \"START\" AdminController.updateMember() | Get()");
		log.debug(" ├[param] memberNo : "+memberNo);
		
		// 1. 기존 Member 정보 조회
		Member member = memberService.getSelectMemberOne(memberNo);
		
		// 2. Member 정보 전달
		model.addAttribute("member", member);
		
		return "admin/updateMember";
	}
	@PostMapping("/admin/updateMember")
	public String updateMember(Member member) {
		log.debug("[Debug] \"START\" AdminController.updateMember() | Post()");
		log.debug(" ├[param] member : "+member.toString());
		
		// 1. Member 정보 변경
		int row =  memberService.getUpdateMember(member);
		
		// 2. 실패 시 
		if(row == 0) {
			log.warn("[WARN] \"FAILED\" update member");
			return "admin/memberOne?memberNo="+member.getMemberNo();
		}
		
		log.debug("[Debug] \"SUCCESS\" update member");
		
		return "admin/memberOne";
	}
	
/* Member 상세 조회 */
	@GetMapping("/admin/memberOne")
	public String memberOne(Model model, int memberNo) {
		log.debug("[Debug] \"START\" AdminController.memberOne() | Get()");
		log.debug(" ├[param] memberNo : "+memberNo);
		
		// 1. Member 상세정보 조회
		Member member = memberService.getSelectMemberOne(memberNo);
		
		// 2. 조회된 Member 정보 전달
		model.addAttribute("member", member);
		
		return "admin/memberOne";
	}
	
/* Member 목록 조회 */
	@GetMapping("/admin/memberList")
	public String memberList(Model model, 
			@RequestParam(defaultValue = "1") int currentPage, 
			@RequestParam(required = false) String memberId) {
		log.debug("[Debug] \"START\" AdminController.memberList() | Get()");
		log.debug(" ├[param] currentPage : "+currentPage);
		log.debug(" ├[param] memberId : "+memberId);
		
		// 1. Member 목록 조회
		Map<String, Object> map = memberService.getSelectMemberListByMemberId(memberId, currentPage, ROW_PER_PAGE);
		
		// 2. 조회된 데이터 가공
		model.addAttribute("memberList", map.get("memberList"));
		model.addAttribute("memberId", memberId);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "admin/memberList";
	}
	
/* Admin 인덱스 호출 */
	@GetMapping("/admin/adminIndex")
	public String adminIndx() {
		return "admin/adminIndex";
	}
}
