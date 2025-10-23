package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = { MemberMapper.class, ReservationSubjectMapper.class })
public interface ReserveMapper {

    @Mapping(target = "member", ignore = true)
    Reservation infoReqDtoToEntity(ReserveInfoReqDTO reserveInfoReqDTO);

    @Mapping(source = "member.id", target = "memberId")
    ReserveResDTO entityToResDto(Reservation reservation);

    List<ReserveResDTO> entityToResDtoList(List<Reservation> reservationList);
}
