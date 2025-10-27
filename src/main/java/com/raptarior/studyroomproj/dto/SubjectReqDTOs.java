package com.raptarior.studyroomproj.dto;

public class SubjectReqDTOs {
    public record Create(String subjectName, Long memberId) {}
    public record Update(String subjectName) {}
}
