package com.ssafy.db.repository;

import com.ssafy.db.entity.Foundation;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoundationRepository extends JpaRepository<Foundation, Long> {

    Foundation findByFoundationSeq(int foundationSeq);

    @Override
    List<Foundation> findAll();
}
