package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import com.raptarior.studyroomproj.dto.MemberResDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void joinAndFindMember() {
        MemberInfoReqDTO dto = MemberInfoReqDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();

        Long resultId = memberService.joinMember(dto);
        MemberResDTO result = memberService.findMember(resultId);

        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(dto);

    }

    @Test
    void joinAndModifyMember() {
        MemberInfoReqDTO origin = MemberInfoReqDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();
        Long resultId = memberService.joinMember(origin);

        MemberInfoReqDTO changed = MemberInfoReqDTO.builder()
                .nickname("rich man")
                .password("54321")
                .build();
        memberService.modifyMember(resultId, changed);

        MemberResDTO result = memberService.findMember(resultId);
        assertThat(result).usingRecursiveComparison()
                .comparingOnlyFields("nickname", "password")
                .isEqualTo(changed);
    }

    @Test
    void joinAndRemoveMember() {
        MemberInfoReqDTO dto = MemberInfoReqDTO.builder()
                .email("testAccount@google.com")
                .nickname("cake man")
                .password("12345")
                .build();

        Long resultId = memberService.joinMember(dto);

        memberService.removeMember(resultId);

        assertThatThrownBy(() -> memberService.findMember(resultId))
                .isInstanceOf(NoSuchElementException.class);
    }
}