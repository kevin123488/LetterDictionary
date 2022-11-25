package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 팔로우 모델 정의.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int followSeq;

    String myId;
    String followId;

    @CreationTimestamp
    @Column(name = "REG_DTM", nullable = false)
    private LocalDateTime REG_DTM;

    @UpdateTimestamp
    @Column(name = "MOD_DTM", nullable = true)
    private LocalDateTime MOD_DTM;

}

