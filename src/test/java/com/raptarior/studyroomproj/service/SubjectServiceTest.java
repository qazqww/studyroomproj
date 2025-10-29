package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import com.raptarior.studyroomproj.dto.SubjectReqDTOs;
import com.raptarior.studyroomproj.dto.SubjectResDTO;
import com.raptarior.studyroomproj.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SubjectServiceTest {

    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    MemberService memberService;

    Long memberId;

    @BeforeEach
    void createMember() {
        MemberInfoReqDTO memberInfoReqDTO = MemberInfoReqDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();

        memberId = memberService.joinMember(memberInfoReqDTO);
    }

    @Test
    void getSubject() {
        SubjectReqDTOs.Create dto = new SubjectReqDTOs.Create("my subject", memberId);

        Long subjectId = subjectService.createSubject(dto);
        SubjectResDTO result = subjectService.getSubject(subjectId);

        assertThat(result.getSubjectName()).isEqualTo(dto.subjectName());
    }

    @Test
    void getSubjectList() {
        SubjectReqDTOs.Create dto1 = new SubjectReqDTOs.Create("my subject1", memberId);
        SubjectReqDTOs.Create dto2 = new SubjectReqDTOs.Create("my subject2", memberId);
        SubjectReqDTOs.Create dto3 = new SubjectReqDTOs.Create("my subject3", memberId);

        subjectService.createSubject(dto1);
        subjectService.createSubject(dto2);
        subjectService.createSubject(dto3);
        List<SubjectResDTO> subjectResDTOList = subjectService.getSubjectList(memberId);

        assertThat(subjectResDTOList.get(0).getSubjectName()).isEqualTo(dto1.subjectName());
        assertThat(subjectResDTOList.get(1).getSubjectName()).isEqualTo(dto2.subjectName());
        assertThat(subjectResDTOList.get(2).getSubjectName()).isEqualTo(dto3.subjectName());
    }

    @Test
    void updateSubject() {
        SubjectReqDTOs.Create dto = new SubjectReqDTOs.Create("old subject", memberId);
        Long subjectId = subjectService.createSubject(dto);

        SubjectReqDTOs.Update newDto = new SubjectReqDTOs.Update("new subject");
        subjectService.updateSubject(subjectId, newDto);

        SubjectResDTO result = subjectService.getSubject(subjectId);

        assertThat(result.getSubjectName()).isEqualTo(newDto.subjectName());
    }

    @Test
    void deleteSubject() {
        SubjectReqDTOs.Create dto = new SubjectReqDTOs.Create("old subject", memberId);
        Long subjectId = subjectService.createSubject(dto);

        subjectService.deleteSubject(subjectId);

        assertThatThrownBy(() -> subjectService.getSubject(subjectId))
                .isInstanceOf(NoSuchElementException.class);
    }
}