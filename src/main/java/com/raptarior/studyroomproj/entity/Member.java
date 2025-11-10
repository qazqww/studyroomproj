package com.raptarior.studyroomproj.entity;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    // 소셜 로그인 추가 시 (후에 구현 예정)
    // private String provider;
    // private String providerId;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Subject> subjects = new ArrayList<>();

    @Builder
    private Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static Member create(MemberInfoReqDTO dto, BCryptPasswordEncoder encoder) {
        return Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(encoder.encode(dto.getPassword())).build();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        this.password = password;
    }

}
