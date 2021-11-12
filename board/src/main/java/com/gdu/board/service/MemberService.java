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
	
	public Member getMemberLogin(Member member) {
		log.debug("[Debug] \"START\" MemberService.getMemberLogin()");
		log.debug(" ├[param] member : "+member.toString());
		return memberMapper.selectMemberOne(member);
	}
}
