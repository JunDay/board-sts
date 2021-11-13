package com.gdu.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.board.vo.Comment;

@Mapper
public interface CommentMapper {
	/* [삭제] Commentr */
	void deleteComment(Comment comment);
	/* [삽입] Commnet */
	void insertComment(Comment comment);
}
