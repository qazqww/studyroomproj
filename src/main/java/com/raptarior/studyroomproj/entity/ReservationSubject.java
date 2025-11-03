package com.raptarior.studyroomproj.entity;

import jakarta.persistence.*;

@Entity
public class ReservationSubject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
