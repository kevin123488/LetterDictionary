package com.ssafy.api.service;

import com.ssafy.api.request.DonationRegisterPostReq;
import com.ssafy.db.entity.Donation;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface DonationService {

	Donation createDonation(DonationRegisterPostReq donationRegisterInfo);
	Donation getDonationByDonationSeq(int donationSeq);
	List<Donation> getDonationByUserSeq(int userSeq);
	List<Donation> getDonationByFoundationSeq(int foundationSeq);

}
