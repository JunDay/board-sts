package com.gdu.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.BoardMapper;
import com.gdu.board.vo.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	public int selectTotalBoardCountByCategory(String categoryName) {
		log.debug("[Debug] \"START\" BoardService.selectTotalBoardCountByCategory()");
		log.debug(" ├[param] categoryName : "+categoryName);
		
		return boardMapper.selectBoardTotalCount(categoryName);
	}
	
	/* 보드 삭제 */
	public void deleteBoard(int boardNo) {
		log.debug("[Debug] \"START\" BoardService.deleteBoard()");
		log.debug(" ├[param] boardNo : "+boardNo);
		
		boardMapper.deleteBoard(boardNo);
	}
	/* 보드 추가 */
	public void addBoard(Board board) {
		log.debug("[Debug] \"START\" BoardService.getBoardOneAndComments()");
		log.debug(" ├[param] board : "+board.toString());
		
		boardMapper.insertBoard(board);
	}
	
	/* 한 Board와 Comment 목록 조회 */
	// SELECT -> 1 : Board, N : Comment
	public Board getBoardOneAndComments(int boardNo) {
		log.debug("[Debug] \"START\" BoardService.getBoardOneAndComments()");
		log.debug(" ├[param] boardNo : "+boardNo);
		
		return boardMapper.selectBoardOneAndComments(boardNo);
	}
	
	/* Board 목록 조회 */
	// SELECT -> N : Board
	public Map<String, Object> getBoardListByCategory(String boardCategory, int currentPage, int rowPerPage){
		log.debug("[Debug] \"START\" BoardService.getBoardListByCategory()");
		log.debug(" ├[param] boardCategory : "+boardCategory);
		log.debug(" ├[param] currentPage : "+currentPage);
		log.debug(" ├[param] rowPerPage : "+rowPerPage);
		
		// 1. 매개변수 가공 (paraMap <-- boardCategory, currentPage, rowPerPage)
		Map<String, Object> paraMap = new HashMap<>();
		int beginRow = (rowPerPage-1) * currentPage;
		
		paraMap.put("boardCategory", boardCategory);		
		paraMap.put("beginRow", beginRow);
		paraMap.put("rowPerPage", rowPerPage);
		
		// 2. board 리스트 조회
		List<Board> boardList = boardMapper.selectBoardListByCategory(paraMap);
		
		// 3. 리턴 값 가공 (return : boardList & lastPage)
		Map<String, Object> returnMap = new HashMap<>();
		
		int lastPage = 0;
		int totalCount = boardMapper.selectBoardTotalCount(boardCategory);
		
		lastPage = totalCount / rowPerPage;
		if(totalCount%rowPerPage !=0) {
			lastPage += 1;
		}
		log.debug(" ├[param] lastPage : "+lastPage);
		returnMap.put("boardList", boardList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
}
