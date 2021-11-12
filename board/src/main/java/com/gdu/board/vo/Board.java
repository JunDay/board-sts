package com.gdu.board.vo;

import java.util.List;

import lombok.Data;

@Data
public class Board {
	private int boardNo;				// [PK] Board 식별 번호, AUTO_INCREMENT
	private String boardCategory;		// [FK] Board 카테고리, Category 테이블 참조, 식별
	private String boardTitle;
	private String boardContent;
	private String memberId;			// Board 작성자 ID, 오직 작성자만 변경 가능
	private String boardDate;			// Board이 작성된 날짜
	private String updateDate;			// Board이 수정된 날짜
	private List<Comment> comments;		// 하나의 Board에 작성된 댓글들 | Board 1:N Comment
}
