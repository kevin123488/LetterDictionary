package com.ssafy.db.repository;


import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserSeq(int userSeq);
    User findByUserId(String userId);
}
