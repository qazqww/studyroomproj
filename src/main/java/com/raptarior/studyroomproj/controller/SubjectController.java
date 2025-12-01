package com.raptarior.studyroomproj.controller;

import com.raptarior.studyroomproj.dto.SubjectReqDTOs;
import com.raptarior.studyroomproj.dto.SubjectResDTO;
import com.raptarior.studyroomproj.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/subject")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Void> createSubject(@RequestBody SubjectReqDTOs.Create req) {
        Long subjectId = subjectService.createSubject(req);
        URI location = URI.create("/subject/" + subjectId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectResDTO> getSubject(@PathVariable Long subjectId) {
        SubjectResDTO subjectResDTO = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(subjectResDTO);
    }

    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<SubjectResDTO>> getSubjectList(@PathVariable Long memberId) {
        List<SubjectResDTO> subjectResDTOList =  subjectService.getSubjectList(memberId);
        return ResponseEntity.ok(subjectResDTOList);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Void> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectReqDTOs.Update req) {
        subjectService.updateSubject(subjectId, req);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

}
