package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.*;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Subject;
import com.raptarior.studyroomproj.repository.MemberRepository;
import com.raptarior.studyroomproj.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final MemberRepository memberRepository;

    public Long createSubject(SubjectReqDTOs.Create req) {
        Member member = memberRepository.findById(req.memberId()).orElseThrow();

        Subject subject = Subject.builder()
                .subjectName(req.subjectName())
                .member(member).build();

        Subject result = subjectRepository.save(subject);
        return result.getId();
    }

    public SubjectResDTO getSubject(Long subjectId) {
        Subject result = subjectRepository.findById(subjectId).orElseThrow();
        SubjectResDTO subjectResDTO = subjectMapper.entityToResDto(result);
        return subjectResDTO;
    }

    public List<SubjectResDTO> getSubjectList(Long memberId) {
        List<Subject> result = subjectRepository.findByMember_Id(memberId);
        List<SubjectResDTO> subjectResDTOList = subjectMapper.entityToResDtoList(result);
        return subjectResDTOList;
    }

    @Transactional
    public void updateSubject(Long subjectId, SubjectReqDTOs.Update req) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        subject.changeSubjectName(req.subjectName());
    }

    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
