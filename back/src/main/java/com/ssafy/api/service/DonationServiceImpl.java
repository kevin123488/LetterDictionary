package com.ssafy.api.service;

import com.ssafy.api.request.DonationRegisterPostReq;

import com.ssafy.db.entity.Donation;
import com.ssafy.db.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("DonationService")
public class DonationServiceImpl implements DonationService {

	@Autowired
    DonationRepository donationRepository;

    @Override
    public Donation createDonation(DonationRegisterPostReq donationRegisterInfo) {

        Donation donation = new Donation();

        donation.setFoundationSeq(donationRegisterInfo.getFoundationSeq());
        donation.setUserSeq(donationRegisterInfo.getUserSeq());
        donation.setDonationImgUrl(donationRegisterInfo.getDonationImgUrl());
        donation.setDonationText(donationRegisterInfo.getDonationText());
        donation.setDonationPay(donationRegisterInfo.getDonationPay());

        return donationRepository.save(donation);
    }

    @Override
    public Donation getDonationByDonationSeq(int donationSeq) {

        Donation donation = donationRepository.findByDonationSeq(donationSeq);

        return donation;
    }

    @Override
    public List<Donation> getDonationByUserSeq(int userSeq) {

        List<Donation> donations = donationRepository.findByUserSeq(userSeq);

        return donations;
    }

    @Override
    public List<Donation> getDonationByFoundationSeq(int foundationSeq) {

        List<Donation> donations = donationRepository.findByFoundationSeq(foundationSeq);

        return donations;
    }

}
