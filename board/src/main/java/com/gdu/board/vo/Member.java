package com.gdu.board.vo;

import lombok.Data;

@Data
public class Member {
	private int memberNo;		// [PK] Member 식별 번호, AUTO_INCREMENT
	private String memberId;	// [UK] Member ID
	private String memberPw;
	private int memberLevel;
	private int memberState;
	private String memberEmail;
	private String memberDate;	// Member가 최초로 생성된 날짜
	private String updateDate;
}
