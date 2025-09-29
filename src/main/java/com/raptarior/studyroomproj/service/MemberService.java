package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import com.raptarior.studyroomproj.dto.MemberResDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public Long joinMember(MemberInfoReqDTO memberPostReqDTO) {
        // 임시 코드
        Long memberId = 0L;
        return memberId;
    }

    public MemberResDTO findMember(Long memberId) {
        // 임시 코드
        return MemberResDTO.builder().build();
    }

    public void modifyMember(Long memberId, MemberInfoReqDTO memberInfoReqDTO) {

    }

    public void removeMember(Long memberId) {

    }
}
