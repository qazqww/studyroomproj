package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.ReservationSubject;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class ReserveResDTO {

    private Long id;

    private Long roomNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReserveStatus status;

    private LocalDateTime createdTime;

    private Member member;

    private List<ReservationSubject> reservationSubjects;
}
