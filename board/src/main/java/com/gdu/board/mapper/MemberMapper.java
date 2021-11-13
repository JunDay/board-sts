package com.gdu.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Member;

@Mapper
public interface MemberMapper {
	
	/* Member 회원가입 */
	int insertMember(Member member);
	/* ID 중복 확인 */
	int selectMemberIdCheck(String memberId);
	/* Member 로그인 */
	Member selectMemberOne(Member member);
}
