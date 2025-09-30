package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member infoReqDtoToEntity(MemberInfoReqDTO memberInfoReqDTO);

    MemberResDTO toResDto(Member member);

    Member resDtoToEntity(MemberResDTO dto);
}
