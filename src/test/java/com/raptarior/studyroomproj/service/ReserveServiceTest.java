package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.dto.*;
import com.raptarior.studyroomproj.entity.Member;
import org.junit.jupiter.api.BeforeAll;
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
    @Autowired
    MemberMapper memberMapper;

    Member member1;
    Member member2;

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

        Long memberId1 = memberService.joinMember(dto);
        Long memberId2 = memberService.joinMember(dto2);
        member1 = memberMapper.resDtoToEntity(memberService.findMember(memberId1));
        member2 = memberMapper.resDtoToEntity(memberService.findMember(memberId2));
    }

    @Test
    void createAndGetReserve() {
        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(2))
                .member(member1)
                .reservationSubjects(new ArrayList<>()).build();

        Long resultId = reserveService.createReservation(dto);
        ReserveResDTO result = reserveService.getReservation(new ReserveOtherReqDTO.GetReserve(resultId));

        System.out.println("DTO " + dto);
        System.out.println("Result " + result);

        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(dto);
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