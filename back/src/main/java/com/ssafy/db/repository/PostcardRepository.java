package com.ssafy.db.repository;

import com.ssafy.common.customObj.PostcardSearchList;
import com.ssafy.common.customObj.TopPostcardList;
import com.ssafy.db.entity.Postcard;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 엽서 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface PostcardRepository extends JpaRepository<Postcard, Integer> {

    List<Postcard> findAllByUserSeq(int userSeq);

    @Query(value = "SELECT postcard_seq, count(*) AS like_cnt " +
            " FROM postcard_like GROUP BY postcard_seq " +
            " ORDER BY count(*) DESC, postcard_seq DESC LIMIT 0, 10", nativeQuery = true)
    List<TopPostcardList> getPostcardTopTen();

    @Query(value = "SELECT DISTINCT p.postcard_seq " +
            " FROM tag t, postcard p " +
            " WHERE t.postcard_seq = p.postcard_seq AND t.tag_content LIKE concat('%', :searchWord, '%')", nativeQuery = true)
    List<PostcardSearchList> getPostcardSearchList(@Param(value = "searchWord") String searchWord);
}
