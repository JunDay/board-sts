package com.gdu.board.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentNo;				// [PK] Comment 식별 키, AUTO_INCREMENT
	private int boardNo;				// [FK] Comment가 작성된 Board, Board 참조, 식별
	private String commentContent;
	private String commentWriter;
	private String commentPw;			// Comment를 수정, 삭제하기 위한 PW
	private String commentDate;
	private String updateDate;
	private Board board;				// Comment가 작성된 Board의 데이터 | Comment 1:1 Board
}
