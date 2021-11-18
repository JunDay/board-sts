package com.gdu.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Membered;

@Mapper
public interface MemberedMapper {
	
	int insertMembered(String memberId);
	
	// 삭제된 멤버 목록 조회
	List<Membered> selectMembedList();
}
