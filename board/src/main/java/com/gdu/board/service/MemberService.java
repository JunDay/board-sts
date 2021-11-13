package com.gdu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.MemberMapper;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	/* 회원가입 */
	public int getInsertMember(Member member) {
		log.debug("[Debug] \"START\" MemberService.getInsertMember()");
		log.debug(" ├[param] member : "+member.toString());
		return memberMapper.insertMember(member);
	}
	
	/* 중복 확인 */
	public int getMemberIdCheck(String memberId) {
		log.debug("[Debug] \"START\" MemberService.getMemberIdCheck()");
		log.debug(" ├[param] memberId : "+memberId);
		return memberMapper.selectMemberIdCheck(memberId);
	}
	
	/* 로그인 */
	public Member getMemberLogin(Member member) {
		log.debug("[Debug] \"START\" MemberService.getMemberLogin()");
		log.debug(" ├[param] member : "+member.toString());
		return memberMapper.selectMemberOne(member);
	}
}
