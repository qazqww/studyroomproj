package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import com.raptarior.studyroomproj.dto.MemberMapper;
import com.raptarior.studyroomproj.dto.MemberResDTO;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Long joinMember(MemberInfoReqDTO memberInfoReqDTO) {
        Member newMember = memberMapper.infoReqDtoToEntity(memberInfoReqDTO);
        Member result = memberRepository.save(newMember);
        return result.getId();
    }

    public MemberResDTO findMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberResDTO memberResDTO = memberMapper.toResDto(member);
        return memberResDTO;
    }

    @Transactional
    public void modifyMember(Long memberId, MemberInfoReqDTO memberInfoReqDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        // 이메일 변경은 불가능
        member.changeNickname(memberInfoReqDTO.getNickname());
        member.changePassword(memberInfoReqDTO.getPassword());
    }

    public void removeMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
