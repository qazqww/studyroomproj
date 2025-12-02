package com.raptarior.studyroomproj.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.TestOnly;

@Entity
public class ReservationSubject {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Builder
    private ReservationSubject(Reservation reservation, Subject subject) {
        this.reservation = reservation;
        this.subject = subject;
    }

    @TestOnly
    public void setId(Long id) {
        this.id = id;
    }
}
