package com.ssafy.db.repository;

import com.ssafy.common.customObj.FoundationSearchList;
import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoundationRepository extends JpaRepository<Foundation, Long> {

    Foundation findByFoundationSeq(int foundationSeq);

    @Override
    List<Foundation> findAll();

    @Query(value = "SELECT foundation_seq, foundation_name, foundation_content, foundation_logo_url " +
            " FROM foundation WHERE foundation_name LIKE concat('%', :searchWord, '%')", nativeQuery = true)
    List<FoundationSearchList> selectFoundationSearchList(@Param(value = "searchWord") String searchWord);
}
