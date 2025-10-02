package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.ReservationSubject;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ReserveInfoReqDTO {

    private Long roomNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReserveStatus status;

    private Member member;

    private List<ReservationSubject> reservationSubjects;
}
