package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Reservation;
import com.raptarior.studyroomproj.entity.ReservationSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ReserveMapperTest {

    private final ReserveMapper reserveMapper = Mappers.getMapper(ReserveMapper.class);

    Member member;

    @BeforeEach
    void createMember() {
        member = Member.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();
    }

    @Test
    void reserveToResDtoTest() {
        ReservationSubject rs1 = ReservationSubject.builder().build();
        ReservationSubject rs2 = ReservationSubject.builder().build();
        rs1.setId(1L);
        rs2.setId(2L);

        Reservation reservation = Reservation.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1L))
                .member(member)
                .reservationSubjects(new ArrayList<>(Arrays.asList(rs1, rs2))).build();

        ReserveResDTO reserveResDTO = reserveMapper.entityToResDto(reservation);
        assertThat(reservation.getRoomNo()).isEqualTo(reserveResDTO.getRoomNo());
        assertThat(reservation.getStartTime()).isEqualTo(reserveResDTO.getStartTime());
        assertThat(reservation.getEndTime()).isEqualTo(reserveResDTO.getEndTime());
        assertThat(reservation.getMember().getId()).isEqualTo(reserveResDTO.getMemberId());

        Set<Long> dtoRsvSubjectIdSet = reserveResDTO.getReservationSubjectIds().stream().collect(Collectors.toSet());
        assertThat(dtoRsvSubjectIdSet).containsAll(
                reservation.getReservationSubjects().stream().map(ReservationSubject::getId).toList()
        );
    }

    @Test
    void reqDtoToReserveTest() {
        ReserveInfoReqDTO reserveInfoReqDTO = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(2))
                .memberId(member.getId())
//                .subjectIds(new ArrayList<>(Arrays.asList(1L, 2L)))
                .build();

        Reservation reservation = reserveMapper.infoReqDtoToEntity(reserveInfoReqDTO);
        assertThat(reservation.getRoomNo()).isEqualTo(reserveInfoReqDTO.getRoomNo());
        assertThat(reservation.getStartTime()).isEqualTo(reserveInfoReqDTO.getStartTime());
        assertThat(reservation.getEndTime()).isEqualTo(reserveInfoReqDTO.getEndTime());

//        List<ReservationSubject> reservationSubjects = reserveSubjectRepository.findAllById(reserveInfoReqDTO.getSubjectIds());
//        reservation.setReservationSubjects(reservationSubjects);
//
//        Set<Long> ids = reservation.getReservationSubjects().stream().map(ReservationSubject::getId).collect(Collectors.toSet());
//        assertThat(ids).containsAll(reserveInfoReqDTO.getSubjectIds());
    }
}
