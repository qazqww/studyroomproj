package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Reservation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        Reservation reservation = Reservation.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1L))
                .member(member)
                .reservationSubjects(new ArrayList<>()).build();

        ReserveResDTO reserveResDTO = reserveMapper.entityToResDto(reservation);
        assertThat(reservation.getRoomNo()).isEqualTo(reserveResDTO.getRoomNo());
        assertThat(reservation.getStartTime()).isEqualTo(reserveResDTO.getStartTime());
        assertThat(reservation.getEndTime()).isEqualTo(reserveResDTO.getEndTime());
        assertThat(reservation.getMember()).isEqualTo(reserveResDTO.getMember());
        assertThat(reservation.getReservationSubjects()).isEqualTo(reserveResDTO.getReservationSubjects());
    }

    @Test
    void reqDtoToReserveTest() {
        ReserveInfoReqDTO reserveInfoReqDTO = ReserveInfoReqDTO.builder()
                .roomNo(1L)
                .startTime(LocalDateTime.now().plusHours(1))
                .endTime(LocalDateTime.now().plusHours(2))
                .member(member)
                .reservationSubjects(new ArrayList<>()).build();

        Reservation reservation = reserveMapper.infoReqDtoToEntity(reserveInfoReqDTO);
        assertThat(reservation.getRoomNo()).isEqualTo(reserveInfoReqDTO.getRoomNo());
        assertThat(reservation.getStartTime()).isEqualTo(reserveInfoReqDTO.getStartTime());
        assertThat(reservation.getEndTime()).isEqualTo(reserveInfoReqDTO.getEndTime());
        assertThat(reservation.getMember()).isEqualTo(reserveInfoReqDTO.getMember());
        assertThat(reservation.getReservationSubjects()).isEqualTo(reserveInfoReqDTO.getReservationSubjects());
    }
}
