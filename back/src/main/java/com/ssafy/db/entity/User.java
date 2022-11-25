package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userSeq;

    int userCode;
    String userId;
    String userPassword;
    String userName;
    String userPhone;
    String userEmail;
    String userProfileUrl;

    int userTemplate;
    int userRemind1;
    int userRemind2;
    int userRemind3;
    int userRemind4;
    int userRemind5;
    int userRemind6;
    int userRemind7;
    int userRemind8;
    int userRemind9;
    int userRemind10;
    int userRemind11;
    int userRemind12;

    @CreationTimestamp
    @Column(name = "REG_DTM", nullable = false)
    private LocalDateTime REG_DTM;

    @UpdateTimestamp
    @Column(name = "MOD_DTM", nullable = true)
    private LocalDateTime MOD_DTM;
}
