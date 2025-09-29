package com.raptarior.studyroomproj.dto;

import lombok.Builder;

@Builder
public class MemberResDTO {

    private Long id;

    private String email;

    private String nickname;

    private String password;
}
