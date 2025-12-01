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
    public ResponseEntity<Void> joinMember(@RequestBody MemberInfoReqDTO memberPostReqDTO) {
        Long memberId = memberService.joinMember(memberPostReqDTO);
        URI location = URI.create("/member/" + memberId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResDTO> findMember(@PathVariable Long memberId) {
        MemberResDTO memberResDTO = memberService.findMember(memberId);
        return ResponseEntity.ok(memberResDTO);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> modifyMember(@PathVariable Long memberId, @RequestBody MemberInfoReqDTO memberInfoReqDTO) {
        memberService.modifyMember(memberId, memberInfoReqDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long memberId) {
        memberService.removeMember(memberId);
        return ResponseEntity.noContent().build();
    }
}