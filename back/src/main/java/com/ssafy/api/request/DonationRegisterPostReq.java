package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 기부 관리 API ([POST] /api/donations) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("DonationRegisterPostRequest")
public class DonationRegisterPostReq {

	@ApiModelProperty(name="재단_seq", example="1")
	int foundationSeq;
	@ApiModelProperty(name="User_seq", example="1")
	int userSeq;
	@ApiModelProperty(name="엽서 이미지")
	String donationImgUrl;
	@ApiModelProperty(name="엽서 문구", example="안녕하세요")
	String donationText;
	@ApiModelProperty(name="기부금", example="100000")
	int donationPay;

}
