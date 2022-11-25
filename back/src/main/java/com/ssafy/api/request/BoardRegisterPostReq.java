package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 관리 API ([POST] /api/boards) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("BoardRegisterPostRequest")
public class BoardRegisterPostReq {

	@ApiModelProperty(name="게시판 코드", example="201")
	int boardCode;
	@ApiModelProperty(name="User_seq", example="1")
	int userSeq;
	@ApiModelProperty(name="게시판 제목", example = "test_Title")
	String boardTitle;
	@ApiModelProperty(name="게시판 내용", example="test_Content")
	String boardContent;

}
