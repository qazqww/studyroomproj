package com.raptarior.studyroomproj.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResDTO {

    private Long id;

    private String email;

    private String nickname;

    private String password;
}
