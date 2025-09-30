package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.entity.Member;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class MemberMapperTest {

    private final MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @Test
    void memberToDtoTest() {
        Member member = Member.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();

        MemberResDTO memberResDTO = memberMapper.toResDto(member);

        assertThat(member.getEmail()).isEqualTo(memberResDTO.getEmail());
        assertThat(member.getNickname()).isEqualTo(memberResDTO.getNickname());
        assertThat(member.getPassword()).isEqualTo(memberResDTO.getPassword());
    }

    @Test
    public void DtoToMemberTest() {
        MemberResDTO memberResDTO = MemberResDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();

        Member member = memberMapper.resDtoToEntity(memberResDTO);

        assertThat(memberResDTO.getEmail()).isEqualTo(member.getEmail());
        assertThat(memberResDTO.getNickname()).isEqualTo(member.getNickname());
        assertThat(memberResDTO.getPassword()).isEqualTo(member.getPassword());
    }

}