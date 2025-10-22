package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReserveServiceTest {

    @Autowired
    ReserveService reserveService;
    @Autowired
    MemberService memberService;

    Long memberId1, memberId2;

    @BeforeEach
    void createMember() {
        MemberInfoReqDTO dto = MemberInfoReqDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();
        MemberInfoReqDTO dto2 = MemberInfoReqDTO.builder()
                .email("secondAccount@google.com")
                .nickname("macaron")
                .password("353")
                .build();

        memberId1 = memberService.joinMember(dto);
        memberId2 = memberService.joinMember(dto2);
    }

    @Test
    void createAndGetReserve() {
        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(2))
                .memberId(memberId1)
                .reservationSubjects(new ArrayList<>()).build();

        Long resultId = reserveService.createReservation(dto);
        ReserveResDTO result = reserveService.getReservation(new ReserveOtherReqDTO.GetReserve(resultId));

        assertThat(result.getRoomNo()).isEqualTo(dto.getRoomNo());
        assertThat(result.getStartTime()).isEqualTo(dto.getStartTime());
        assertThat(result.getEndTime()).isEqualTo(dto.getEndTime());
        assertThat(result.getMember().getId()).isEqualTo(dto.getMemberId());
        assertThat(result.getReservationSubjects()).isEqualTo(dto.getReservationSubjects());
    }

    @Test
    void getReservation() {
    }

    @Test
    void getReservationList() {
    }

    @Test
    void getEmptyRoomList() {
        reserveService.getEmptyRoomList();
    }

    @Test
    void getAvailableTimeFromRoom() {
    }

    @Test
    void getAvailableRoomListFromTime() {
    }
}