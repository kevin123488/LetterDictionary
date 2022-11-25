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
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int boardSeq;

    int boardCode;
    int userSeq;
    String boardTitle;
    String boardContent;

    @CreationTimestamp
    private LocalDateTime REG_DTM;

    @UpdateTimestamp
    private LocalDateTime MOD_DTM;

}
