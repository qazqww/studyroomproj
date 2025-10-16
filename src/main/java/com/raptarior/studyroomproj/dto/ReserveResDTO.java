package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.entity.ReservationSubject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReserveResDTO {

    private Long id;

    private Long roomNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReserveStatus status;

    private LocalDateTime createdTime;

    private List<ReservationSubject> reservationSubjects = new ArrayList<>();
}
