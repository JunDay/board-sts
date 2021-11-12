package com.gdu.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Member;

@Mapper
public interface MemberMapper {
	/* Member 로그인 */
	Member selectMemberOne(Member member);
}
