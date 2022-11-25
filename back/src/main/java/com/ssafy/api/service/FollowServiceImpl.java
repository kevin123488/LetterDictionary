package com.ssafy.api.service;

import com.ssafy.db.entity.Follow;
import com.ssafy.db.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * 팔로우 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("followService")
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowRepository followRepository;

    /**
     * 팔로우 추가 메서드
     */
    @Override
    public String saveFollow(String myId, String followId) throws IOException {
        Follow follow = followRepository.findFollowByMyIdAndFollowId(myId, followId);
        if (follow == null) {
            Follow insert = new Follow();
            insert.setMyId(myId);
            insert.setFollowId(followId);
            followRepository.save(insert);
            return myId + "가 " + followId + "를 팔로우 했습니다.";
        }

        return "팔로우를 실패했습니다.";
    }

    /**
     * 팔로우 삭제 메서드
     */
    @Override
    @Transactional
    public boolean deleteFollow(String myId, String followId) throws IOException {
        Follow follow = followRepository.findFollowByMyIdAndFollowId(myId, followId);
        if (follow != null) {
            followRepository.deleteFollowByMyIdAndFollowId(myId, followId);
            return true;
        }
        return false;
    }

    /**
     * 팔로우 리스트 조회 메서드
     */
    @Override
    public List<Follow> selectFollowList(String myId) throws IOException {
        return followRepository.findAllByMyId(myId);
    }
}
