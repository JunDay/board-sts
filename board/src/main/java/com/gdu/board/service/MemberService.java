package com.gdu.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.MemberMapper;
import com.gdu.board.vo.Board;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	public Map<String, Object> selectMemberListByMemberId(String memberId, int currentPage, int rowPerPage){
		log.debug("[Debug] \"START\" MemberService.selectMemberList()");
		log.debug(" ├[param] memberId : "+memberId);
		log.debug(" ├[param] currentPage : "+currentPage);
		log.debug(" ├[param] rowPerPage : "+rowPerPage);
		
		// 1. 매개변수 가공 (paraMap <-- boardCategory, currentPage, rowPerPage)
		Map<String, Object> paraMap = new HashMap<>();
		int beginRow = (rowPerPage-1) * currentPage;
		
		paraMap.put("memberId", memberId);		
		paraMap.put("beginRow", beginRow);
		paraMap.put("rowPerPage", rowPerPage);
		
		// 2. board 리스트 조회
		List<Member> memberList = memberMapper.selectMemberList(paraMap);
		
		// 3. 리턴 값 가공 (return : boardList & lastPage)
		Map<String, Object> returnMap = new HashMap<>();
		
		int lastPage = 0;
		int totalCount = memberMapper.selectMemberTotalCount(memberId);
		
		lastPage = totalCount / rowPerPage;
		if(totalCount%rowPerPage !=0) {
			lastPage += 1;
		}
		log.debug(" ├[param] lastPage : "+lastPage);
		returnMap.put("memberList", memberList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
	
	public int selectMemberPwCheck(Member member) {
		log.debug("[Debug] \"START\" MemberService.selectMemberPwCheck()");
		log.debug(" ├[param] member : "+member.toString());
		return memberMapper.selectMemberPwCheck(member);
	}
	
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
