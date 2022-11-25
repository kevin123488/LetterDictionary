package com.ssafy.api.response;

import com.ssafy.db.entity.Donation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 기부 정보 조회 API ([GET] /api/donation/{donationSeq}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("DonationResponse")
public class DonationRes {
	@ApiModelProperty(name="재단 이름", example="유니세프")
	String foundationName;
	@ApiModelProperty(name="User Id", example="홍길동")
	String userId;
	@ApiModelProperty(name="기부seq", example="1")
	int donationSeq;
	@ApiModelProperty(name="엽서이미지 url", example="url")
	String donationImgUrl;
	@ApiModelProperty(name="엽서 내용", example="안녕하세요")
	String donationText;
	@ApiModelProperty(name="기부 금액", example="100000")
	int donationPay;
	
	public static DonationRes of(Donation donation, String foundationName, String userId) {

		DonationRes res = new DonationRes();

		res.setFoundationName(foundationName);
		res.setUserId(userId);
		res.setDonationSeq(donation.getDonationSeq());
		res.setDonationImgUrl(donation.getDonationImgUrl());
		res.setDonationText(donation.getDonationText());
		res.setDonationPay(donation.getDonationPay());

		return res;
	}
}
