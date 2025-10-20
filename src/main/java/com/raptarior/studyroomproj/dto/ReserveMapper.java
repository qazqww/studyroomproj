package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = { MemberMapper.class, ReservationSubjectMapper.class })
public interface ReserveMapper {

    Reservation infoReqDtoToEntity(ReserveInfoReqDTO reserveInfoReqDTO);

    ReserveResDTO entityToResDto(Reservation reservation);

    List<ReserveResDTO> entityToResDtoList(List<Reservation> reservationList);
}
