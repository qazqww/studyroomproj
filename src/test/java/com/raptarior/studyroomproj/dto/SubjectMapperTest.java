package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubjectMapperTest {

    SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);
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
    void reqDtoToEntity() {
        SubjectReqDTOs.Create dto = new SubjectReqDTOs.Create(
                "database study", 1L
        );
        Subject subject = subjectMapper.reqDtoToEntity(dto);

        assertThat(subject.getSubjectName()).isEqualTo(dto.subjectName());
    }

    @Test
    void entityToResDto() {
        Subject subject = Subject.builder()
                .subjectName("java")
                .member(member).build();

        SubjectResDTO dto = subjectMapper.entityToResDto(subject);

        assertThat(dto.getSubjectName()).isEqualTo(subject.getSubjectName());
    }

    @Test
    void entityToResDtoList() {
        Subject subject1 = Subject.builder()
                .subjectName("algorithm beginner")
                .member(member).build();
        Subject subject2 = Subject.builder()
                .subjectName("algorithm middle")
                .member(member).build();
        Subject subject3 = Subject.builder()
                .subjectName("algorithm expert")
                .member(member).build();

        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject1);
        subjectList.add(subject2);
        subjectList.add(subject3);

        List<SubjectResDTO> subjectResDTOList = subjectMapper.entityToResDtoList(subjectList);
        assertThat(subjectResDTOList.get(0).getSubjectName()).isEqualTo(subjectList.get(0).getSubjectName());
        assertThat(subjectResDTOList.get(1).getSubjectName()).isEqualTo(subjectList.get(1).getSubjectName());
        assertThat(subjectResDTOList.get(2).getSubjectName()).isEqualTo(subjectList.get(2).getSubjectName());
    }
}
