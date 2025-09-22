package com.raptarior.studyroomproj.entity;

import com.raptarior.studyroomproj.common.ReserveStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roomNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReserveStatus status;

    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationSubject> reservationSubjects = new ArrayList<>();

    @Builder
    private Reservation(Long roomNo, LocalDateTime startTime, LocalDateTime endTime,
                        Member member, List<ReservationSubject> reservationSubjects) {
        this.roomNo = roomNo;
        this.startTime = startTime;
        this.endTime = endTime;

        LocalDateTime nowTime = LocalDateTime.now();
        this.createdTime = nowTime;
        if (nowTime.isAfter(startTime)) {
            this.status = ReserveStatus.USING;
        }
        else {
            this.status = ReserveStatus.RESERVED;
        }

        this.member = member;
        this.reservationSubjects = reservationSubjects;
    }
}
