package com.poseidon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "board2")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    @Column(length = 50)
    private String btitle;

    @Column(columnDefinition = "mediumtext")
    private String bcontent;

    @Column(name = "boarddate", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime bdate;

    @Column(nullable = false)
    private String name;

    // ✅ 기본 생성자 (JPA 필수)
    public Board() {
    }

    // 기존 생성자
    public Board(String btitle, String bcontent, String name) {
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.name = name;
    }
}
