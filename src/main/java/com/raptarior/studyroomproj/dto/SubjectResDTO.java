package com.raptarior.studyroomproj.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class SubjectResDTO {

    @Setter
    private String subjectName;

    private Long totalStudyCnt;

    private Long totalStudyMinute;

}
