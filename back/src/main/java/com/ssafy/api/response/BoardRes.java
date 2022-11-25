package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 정보 조회 API ([GET] /api/boards/{boardSeq}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("BoardResponse")
public class BoardRes {

	@ApiModelProperty(name="게시판 Seq", example="1")
	int boardSeq;
	@ApiModelProperty(name="User Id", example="홍길동")
	String userId;
	@ApiModelProperty(name="게시판 제목", example="test_Title")
	String boardTitle;
	@ApiModelProperty(name="게시판 내용", example="test_Content")
	String boardContent;

	
	public static BoardRes of(Board board, String userId) {

		BoardRes res = new BoardRes();

		res.setBoardSeq(board.getBoardSeq());
		res.setUserId(userId);
		res.setBoardTitle(board.getBoardTitle());
		res.setBoardContent(board.getBoardContent());

		return res;
	}
}
