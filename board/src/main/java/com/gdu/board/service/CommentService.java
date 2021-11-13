package com.gdu.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.board.mapper.CommentMapper;
import com.gdu.board.vo.Board;
import com.gdu.board.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CommentService {
	@Autowired
	CommentMapper commentMapper;
	
	/* 댓글 삭제 */
	public void deleteComment(Comment comment, int boardNo) {
		log.debug("[Debug] \"START\" CommentService.deleteComment()");
		log.debug(" ├[param] comment.getBoardNo() : "+comment.getBoardNo());
		log.debug(" ├[param] boardNo : "+boardNo);
		
		// 1. 데이터 가공(삭제할 commentNo, boardNo)
		Board board = new Board();
		board.setBoardNo(boardNo);
		comment.setBoard(board);
		
		commentMapper.deleteComment(comment);
	}
	/* 댓글 추가 */
	public void addComment(Comment comment, int boardNo) {
		log.debug("[Debug] \"START\" CommentService.addComment()");
		log.debug(" ├[param] comment : "+comment.toString());
		log.debug(" ├[param] boardNo : "+boardNo);
		
		// 1. 데이터 가공(Comment <- comment + barodNo)
		Board board = new Board();
		board.setBoardNo(boardNo);
		comment.setBoard(board);
		
		commentMapper.insertComment(comment);
	}
}
