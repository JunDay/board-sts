package com.gdu.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Member;

@Mapper
public interface MemberMapper {
	
// 회원 기능
	/* Member PW 변경 */
	int updateMemberPassword(Member member);
	/* Memeber 삭제 */
	int deleteMember(int memberNo);
	/* Member 정보 변경 */
	int updateMember(Member member);
	/* Member 정보 조회 */
	Member selectMemberOne(int memberNo);
	/* Member PW 확인 */
	int selectMemberPwCheck(Member member);

// 관리자 기능
	/* Member 총 수, 페이징 */
	int selectMemberTotalCount(String memberId);
	/* Member List 조회 */
	List<Member> selectMemberList(Map<String, Object> map);
	
// 비회원 기능
	/* Member 회원가입 */
	int insertMember(Member member);
	/* Member 로그인 */
	Member selectMemberLogin(Member member);
}
