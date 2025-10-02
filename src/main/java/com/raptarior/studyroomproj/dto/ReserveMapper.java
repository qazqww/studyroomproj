package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReserveMapper {

    Reservation infoReqDtoToEntity(ReserveInfoReqDTO reserveInfoReqDTO);

    ReserveResDTO entityToResDto(Reservation reservation);
}
