package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Reservation;
import com.raptarior.studyroomproj.entity.ReservationSubject;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = { MemberMapper.class, ReservationSubjectMapper.class })
public interface ReserveMapper {

    @Mapping(target = "member", ignore = true)
    @Mapping(target = "reservationSubjects", ignore = true)
    Reservation infoReqDtoToEntity(ReserveInfoReqDTO reserveInfoReqDTO);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(target = "reservationSubjectIds", ignore = true)
    ReserveResDTO entityToResDto(Reservation reservation);

    @AfterMapping
    default void addReservationSubjectIds(Reservation reservation, @MappingTarget ReserveResDTO reserveResDTO) {
        List<Long> reservationSubjectIds = reservation.getReservationSubjects().stream().map(ReservationSubject::getId).toList();
        reserveResDTO.setReservationSubjectIds(reservationSubjectIds);
    }
}
