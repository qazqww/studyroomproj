package com.raptarior.studyroomproj.repository;

import com.raptarior.studyroomproj.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reservation, Long> {
}
