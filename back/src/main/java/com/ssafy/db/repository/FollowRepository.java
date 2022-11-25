package com.ssafy.db.repository;

import com.ssafy.db.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 팔로우 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Follow findFollowByMyIdAndFollowId(String myId, String FollowId);
    void deleteFollowByMyIdAndFollowId(String myId, String FollowId);
    List<Follow> findAllByMyId(String myId);
}
