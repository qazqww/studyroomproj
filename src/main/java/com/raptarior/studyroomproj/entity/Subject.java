package com.raptarior.studyroomproj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long totalStudyCnt;

    private Long totalStudyMinute;

    @OneToMany(mappedBy = "subject")
    private List<ReservationSubject> reservationSubjects = new ArrayList<>();

    @Builder
    private Subject(String subjectName, Member member) {
        this.subjectName = subjectName;
        this.member = member;
        totalStudyCnt = 0L;
        totalStudyMinute = 0L;
    }

    public void changeSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void addStudyTime(LocalDateTime startTime, LocalDateTime endTime) {
        totalStudyMinute += Duration.between(startTime, endTime).toMinutes();
    }

    public String getStudyTime() {
        return totalStudyMinute / 24L + "시간 " + totalStudyMinute % 24L + "분";
    }

}
