package com.raptarior.studyroomproj.repository;

import com.raptarior.studyroomproj.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByMember_Id(Long memberID);
}
