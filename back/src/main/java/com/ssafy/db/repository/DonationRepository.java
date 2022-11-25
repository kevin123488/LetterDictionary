package com.ssafy.db.repository;

import com.ssafy.db.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    Donation findByDonationSeq(int donationSeq);
    List<Donation> findByUserSeq(int userSeq);
    List<Donation> findByFoundationSeq(int foundationSeq);

}