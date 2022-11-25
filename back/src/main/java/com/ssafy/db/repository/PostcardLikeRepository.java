package com.ssafy.db.repository;

import com.ssafy.db.entity.PostcardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 엽서 좋아요 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface PostcardLikeRepository extends JpaRepository<PostcardLike, Integer> {
    PostcardLike findPostcardLikeByPostcardSeqAndUserSeq(int postcardSeq, int userSeq);
    void deletePostcardLikeByPostcardSeqAndUserSeq(int postcardSeq, int userSeq);
    void deletePostcardLikesByPostcardSeq(int postcardSeq);

    List<PostcardLike> findAllByUserSeq(int userSeq);
}
