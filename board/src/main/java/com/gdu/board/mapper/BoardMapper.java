package com.gdu.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.*;

@Mapper
public interface BoardMapper {
	

	/* Board 삭제 */
	void deleteBoard(int boardNo);

	/* Board 삽입 */
	void insertBoard(Board board);
	
	/* 하나의 Board와 Comment의 목록 조회 */
	// return : 하나의 Board, 한 Board의 모든 Comment
	Board selectBoardOneAndComments(int boardNo);
	
	/* 카테고리를 기준으로 Board의 총 개수 조회, 페이징용 */
	// return : 모든 Board의 개수
	int selectBoardTotalCount(String baordCategory);
	
	/* 모든 Board의 목록 조회 */
	// param : baordCategory, beginRow, rowPerPage
	// return : 모든 Board의 List
	List<Board> selectBoardListByCategory(Map<String, Object> map);
}
