package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 관리 API ([PATCH] /api/boards/{boardSeq}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("BoardUpdatePostRequest")
public class BoardUpdatePostReq {

	@ApiModelProperty(name="게시판 제목", example = "test_Title")
	String boardTitle;
	@ApiModelProperty(name="게시판 내용", example="test_Content")
	String boardContent;

}
