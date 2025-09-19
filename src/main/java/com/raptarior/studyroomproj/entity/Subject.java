package com.raptarior.studyroomproj.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Builder
    private Subject(String subjectName, Member member) {
        this.subjectName = subjectName;
        this.member = member;
    }

}
