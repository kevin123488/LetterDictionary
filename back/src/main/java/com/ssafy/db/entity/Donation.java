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
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int donationSeq;

    int foundationSeq;
    int userSeq;
    String donationImgUrl;
    String donationText;
    int donationPay;

    @CreationTimestamp
    private LocalDateTime REG_DTM;

    @UpdateTimestamp
    private LocalDateTime MOD_DTM;

}
