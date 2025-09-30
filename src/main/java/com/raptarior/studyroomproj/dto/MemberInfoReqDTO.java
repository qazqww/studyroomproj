package com.raptarior.studyroomproj.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 및 회원 수정 DTO
 */
@Builder
@Getter
public class MemberInfoReqDTO {

    private String email;

    private String nickname;

    private String password;
}
