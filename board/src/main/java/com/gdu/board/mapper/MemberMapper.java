package com.gdu.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Member;

@Mapper
public interface MemberMapper {
	
	int selectMemberTotalCount(String memberId);
	
	List<Member> selectMemberList(Map<String, Object> map);
	/* 회원PW 확인*/
	int selectMemberPwCheck(Member member);
	/* Member 회원가입 */
	int insertMember(Member member);
	/* ID 중복 확인 */
	int selectMemberIdCheck(String memberId);
	/* Member 로그인 */
	Member selectMemberOne(Member member);
}
