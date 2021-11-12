package com.gdu.board.vo;

import lombok.Data;

@Data
public class Membered {
	private String memberId;		// [PK] 삭제된 Member의 ID
	private String memberedDate;	// Member가 삭제된 날짜
}
