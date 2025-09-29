package com.raptarior.studyroomproj.controller;

import com.raptarior.studyroomproj.dto.MemberInfoReqDTO;
import com.raptarior.studyroomproj.dto.MemberResDTO;
import com.raptarior.studyroomproj.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    private ResponseEntity<Void> joinMember(MemberInfoReqDTO memberPostReqDTO) {
        Long memberId = memberService.joinMember(memberPostReqDTO);
        URI location = URI.create("/member/" + memberId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{memberId}")
    private ResponseEntity<MemberResDTO> findMember(@PathVariable Long memberId) {
        MemberResDTO memberResDTO = memberService.findMember(memberId);
        return ResponseEntity.ok(memberResDTO);
    }

    @PutMapping("/{memberId}")
    private ResponseEntity<Void> modifyMember(@PathVariable Long memberId, MemberInfoReqDTO memberInfoReqDTO) {
        memberService.modifyMember(memberId, memberInfoReqDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    private ResponseEntity<Void> removeMember(@PathVariable Long memberId) {
        memberService.removeMember(memberId);
        return ResponseEntity.noContent().build();
    }
}