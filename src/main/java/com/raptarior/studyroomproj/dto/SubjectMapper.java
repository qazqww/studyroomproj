package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(target = "member", ignore = true)
    Subject reqDtoToEntity(SubjectReqDTOs.Create dto);

    SubjectResDTO entityToResDto(Subject subject);

    List<SubjectResDTO> entityToResDtoList(List<Subject> subjectList);

}
