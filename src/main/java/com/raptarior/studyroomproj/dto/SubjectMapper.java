package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject reqDtoToEntity(SubjectReqDTOs.Create dto);

    SubjectResDTO entityToResDto(Subject subject);

    List<SubjectResDTO> entityToResDtoList(List<Subject> subjectList);

}
