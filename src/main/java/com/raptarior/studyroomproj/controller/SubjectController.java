package com.raptarior.studyroomproj.controller;

import com.raptarior.studyroomproj.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/subject")
@RestController
public class SubjectController {

    private final SubjectService subjectService;

}
