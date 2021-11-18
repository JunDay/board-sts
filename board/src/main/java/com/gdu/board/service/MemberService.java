package com.gdu.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.MemberMapper;
import com.gdu.board.mapper.MemberedMapper;
import com.gdu.board.vo.Board;
import com.gdu.board.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MemberedMapper memberedMapper;
	
	/* [회원] PW 변경 */
	public int getUpdateMemberPassword(Member member, String newMemberPw) {
		log.debug("[Debug] \"START\" MemberService.updateMemberPassword()");
		
		// 1. 바꿀 PW 삽입
		member.setMemberPw(newMemberPw);
		
		return memberMapper.updateMemberPassword(member);
	}
	
	/* [회원, 관리자] Member 삭제 */
	public int getDeleteMember(HttpSession session, Member member) {
		log.debug("[Debug] \"START\" MemberService.updateMember()");
		
		// 0. 로그인된 세션 정보 조회
		Member loginMember = (Member)session.getAttribute("loginMember");
		log.debug(" ├[param] loginMember ID : "+loginMember.getMemberId());
		log.debug(" ├[param] member ID : "+member.getMemberId());
		
		// 1. 입력한 ID와 로그인된 ID 비교
		if(!loginMember.getMemberId().equals(member.getMemberId())) {
			log.error("[ERROR] \"FAILED\" delete member | 입력된 ID 불일치 ");
			return 0;
		}
		
		// 2. 입력된 PW 확인
		if(memberMapper.selectMemberPwCheck(member) != 1) {
			log.error("[ERROR] \"FAILED\" delete member | 입력된 정보 불일치 ");
			return 0;
		}
		
		// 3. 삭제할 ID membered 테이블에 추가
		memberedMapper.insertMembered(memberMapper.selectMemberOne(member.getMemberNo()).getMemberId());
		
		// 4. Member 삭제
		return memberMapper.deleteMember(member.getMemberNo());
	}
	
	/* [회원, 관리자] Member 정보 변경 */
	public int getUpdateMember(Member member) {
		log.debug("[Debug] \"START\" MemberService.updateMember()");
		log.debug(" ├[param] member : "+member.toString());
		
		return memberMapper.updateMember(member);
	}
	
	/* [회원, 관리자] Member 정보 조회 */
	public Member getSelectMemberOne(int memberNo) {
		log.debug("[Debug] \"START\" MemberService.selectMemberOne()");
		log.debug(" ├[param] memberNo : "+memberNo);
		return memberMapper.selectMemberOne(memberNo);
	}
	
	/* [관리자] Member 목록 조회 */
	public Map<String, Object> getSelectMemberListByMemberId(String memberId, int currentPage, int rowPerPage){
		log.debug("[Debug] \"START\" MemberService.getSelectMemberListByMemberId()");
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
		
		// 4. 페이징 계산
		int lastPage = 0;
		int totalCount = memberMapper.selectMemberTotalCount(memberId);
		
		lastPage = totalCount / rowPerPage;
		if(totalCount%rowPerPage !=0) {
			lastPage += 1;
		}
		log.debug(" ├[param] lastPage : "+lastPage);
		
		// 5. 리턴 map 가공
		returnMap.put("memberList", memberList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
	
	/* [회원] PW 확인 */
	public int getSelectMemberPwCheck(Member member) {
		log.debug("[Debug] \"START\" MemberService.getSelectMemberPwCheck()");
		log.debug(" ├[param] memberId : "+member.getMemberId());
		return memberMapper.selectMemberPwCheck(member);
	}
	
	/* [공통] 회원가입 */
	public int getInsertMember(Member member) {
		log.debug("[Debug] \"START\" MemberService.getInsertMember()");
		log.debug(" ├[param] member : "+member.toString());
		return memberMapper.insertMember(member);
	}
	
	/* [공통] 로그인 */
	public Member getMemberLogin(Member member) {
		log.debug("[Debug] \"START\" MemberService.getMemberLogin()");
		log.debug(" ├[param] memberId : "+member.getMemberId());
		return memberMapper.selectMemberLogin(member);
	}
}
