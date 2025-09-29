package com.raptarior.studyroomproj.dto;

import lombok.Builder;

/**
 * 회원가입 및 회원 수정 DTO
 */
@Builder
public class MemberInfoReqDTO {

    private String email;

    private String nickname;

    private String password;
}
