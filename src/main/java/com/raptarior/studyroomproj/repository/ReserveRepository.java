package com.raptarior.studyroomproj.repository;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMemberId(Long memberId);

    List<Reservation> findByStatus(ReserveStatus status);

    @Query("""
            SELECT r FROM Reservation r WHERE r.roomNo = :roomNo
            AND r.startTime >= :today AND r.startTime < :tomorrow
            """)
    List<Reservation> findByRoomNoInToday(Long roomNo, LocalDateTime today, LocalDateTime tomorrow);

    @Query("""
            SELECT r FROM Reservation r
            WHERE r.status = 'USING'
            OR r.status = 'RESERVED' AND r.startTime < :time
            """)
    List<Reservation> findByStatusAndTime(LocalDateTime time);
}
