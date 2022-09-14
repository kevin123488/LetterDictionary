package com.ssafy.api.service;

import com.ssafy.db.entity.Follow;

import java.io.IOException;
import java.util.List;

/**
 *	팔로우 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface FollowService {
    String saveFollow(String myId, String followId) throws IOException;
    boolean deleteFollow(String myId, String followId) throws IOException;
    List<Follow> selectFollowList(String myId) throws IOException;
}
