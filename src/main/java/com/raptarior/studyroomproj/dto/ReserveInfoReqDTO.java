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
public class ReserveInfoReqDTO {

    private Long roomNo;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long memberId;

    private List<ReservationSubject> reservationSubjects;
}
