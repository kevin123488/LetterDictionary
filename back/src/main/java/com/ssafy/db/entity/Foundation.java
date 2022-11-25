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
@Table(name="foundation")
public class Foundation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int foundationSeq;

    String foundationName;
    String foundationContent;
    String foundationLogoUrl;

    @CreationTimestamp
    @Column(name = "REG_DTM", nullable = false)
    private LocalDateTime REG_DTM;

    @UpdateTimestamp
    @Column(name = "MOD_DTM", nullable = true)
    private LocalDateTime MOD_DTM;
}
