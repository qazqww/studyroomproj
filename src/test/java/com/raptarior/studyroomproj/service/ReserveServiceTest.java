package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.*;
import com.raptarior.studyroomproj.repository.MemberRepository;
import com.raptarior.studyroomproj.repository.ReserveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReserveServiceTest {

    @Autowired
    ReserveService reserveService;
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
                .reservationSubjectIds(new ArrayList<>()).build();

        Long resultId = reserveService.createReservation(dto);
        ReserveResDTO result = reserveService.getReservation(resultId);

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
                .reservationSubjectIds(new ArrayList<>()).build();
        ReserveInfoReqDTO dto2 = ReserveInfoReqDTO.builder()
                .roomNo(3L)
                .startTime(LocalDateTime.now().plusHours(3))
                .endTime(LocalDateTime.now().plusHours(5))
                .memberId(memberId2)
                .reservationSubjectIds(new ArrayList<>()).build();
        ReserveInfoReqDTO dto3 = ReserveInfoReqDTO.builder()
                .roomNo(5L)
                .startTime(LocalDateTime.now().plusHours(2))
                .endTime(LocalDateTime.now().plusHours(4))
                .memberId(memberId1)
                .reservationSubjectIds(new ArrayList<>()).build();

        reserveService.createReservation(dto);
        reserveService.createReservation(dto2);
        reserveService.createReservation(dto3);

        List<ReserveResDTO> list = reserveService.getReservationList(memberId1);
        List<ReserveResDTO> list2 = reserveService.getReservationList(memberId2);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list2.size()).isEqualTo(1);
    }

    @Test
    void getEmptyRoomList() {
        reserveRepository.deleteAll();
        List<Long> emptyRoomList = reserveService.getEmptyRoomList();
        assertThat(emptyRoomList.size()).isEqualTo(100);

        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(2))
                .memberId(memberId1)
                .reservationSubjectIds(new ArrayList<>()).build();
        ReserveInfoReqDTO dto2 = ReserveInfoReqDTO.builder()
                .roomNo(3L)
                .startTime(LocalDateTime.now().plusHours(3))
                .endTime(LocalDateTime.now().plusHours(5))
                .memberId(memberId2)
                .reservationSubjectIds(new ArrayList<>()).build();

        reserveService.createReservation(dto);
        reserveService.createReservation(dto2);

        emptyRoomList = reserveService.getEmptyRoomList();
        assertThat(emptyRoomList.size()).isEqualTo(99);
    }

    @Test
    void getAvailableTimeFromRoom() {
        reserveRepository.deleteAll();

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(3));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(5));
        LocalDateTime startTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(10));
        LocalDateTime endTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(15));

        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(30L)
                .startTime(startTime)
                .endTime(endTime)
                .memberId(memberId1)
                .reservationSubjectIds(new ArrayList<>()).build();
        ReserveInfoReqDTO dto2 = ReserveInfoReqDTO.builder()
                .roomNo(30L)
                .startTime(startTime2)
                .endTime(endTime2)
                .memberId(memberId2)
                .reservationSubjectIds(new ArrayList<>()).build();

        reserveService.createReservation(dto);
        reserveService.createReservation(dto2);

        List<Integer> timeList = reserveService.getAvailableTimeFromRoom(30L);
        assertThat(timeList).doesNotContain(3, 4, 5, 10, 11, 12, 13, 14, 15);
    }

    @Test
    void getAvailableRoomListFromTime() {
        reserveRepository.deleteAll();

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(20));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(21));
        LocalDateTime startTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(21));
        LocalDateTime endTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(22));

        ReserveInfoReqDTO dto = ReserveInfoReqDTO.builder()
                .roomNo(50L)
                .startTime(startTime)
                .endTime(endTime)
                .memberId(memberId1)
                .reservationSubjectIds(new ArrayList<>()).build();
        ReserveInfoReqDTO dto2 = ReserveInfoReqDTO.builder()
                .roomNo(51L)
                .startTime(startTime2)
                .endTime(endTime2)
                .memberId(memberId2)
                .reservationSubjectIds(new ArrayList<>()).build();

        reserveService.createReservation(dto);
        reserveService.createReservation(dto2);

        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30, 0));
        List<Long> list = reserveService.getAvailableRoomListFromTime(new ReserveOtherReqDTO.GetRoomList(time));
        assertThat(list).doesNotContain(50L);
        assertThat(list).contains(51L);
    }
}