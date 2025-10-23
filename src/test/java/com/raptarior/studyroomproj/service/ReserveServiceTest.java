package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id", "status", "createdTime")
                .isEqualTo(dto);
    }

    @Test
    void createAndGetReservationList() {
        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(2))
                .memberId(memberId1)
                .reservationSubjects(new ArrayList<>()).build();
        ReserveInfoReqDTO dto2 = ReserveInfoReqDTO.builder()
                .roomNo(3L)
                .startTime(LocalDateTime.now().plusHours(3))
                .endTime(LocalDateTime.now().plusHours(5))
                .memberId(memberId2)
                .reservationSubjects(new ArrayList<>()).build();
        ReserveInfoReqDTO dto3 = ReserveInfoReqDTO.builder()
                .roomNo(5L)
                .startTime(LocalDateTime.now().plusHours(2))
                .endTime(LocalDateTime.now().plusHours(4))
                .memberId(memberId1)
                .reservationSubjects(new ArrayList<>()).build();

        reserveService.createReservation(dto);
        reserveService.createReservation(dto2);
        reserveService.createReservation(dto3);

        List<ReserveResDTO> list = reserveService.getReservationList(new ReserveOtherReqDTO.GetMyReserveList(memberId1));
        List<ReserveResDTO> list2 = reserveService.getReservationList(new ReserveOtherReqDTO.GetMyReserveList(memberId2));
        assertThat(list.size()).isEqualTo(2);
        assertThat(list2.size()).isEqualTo(1);
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