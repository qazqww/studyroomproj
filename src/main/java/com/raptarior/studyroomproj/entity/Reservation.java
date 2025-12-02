package com.raptarior.studyroomproj.entity;

import com.raptarior.studyroomproj.common.ReserveStatus;
import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private ReserveStatus status;

    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Setter
    private List<ReservationSubject> reservationSubjects = new ArrayList<>();

    @Builder
    private Reservation(Long roomNo, LocalDateTime startTime, LocalDateTime endTime,
                        Member member, List<ReservationSubject> reservationSubjects) {
        this.roomNo = roomNo;
        this.startTime = startTime;
        this.endTime = endTime;
        if (startTime.isBefore(LocalDateTime.now())) {
            this.status = ReserveStatus.USING;
        }
        else {
            this.status = ReserveStatus.RESERVED;
        }
        this.createdTime = LocalDateTime.now();
        this.member = member;
        this.reservationSubjects = reservationSubjects;
    }
}
