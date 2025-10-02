package com.raptarior.studyroomproj.controller;

import com.raptarior.studyroomproj.dto.ReserveInfoReqDTO;
import com.raptarior.studyroomproj.dto.ReserveOtherReqDTO;
import com.raptarior.studyroomproj.dto.ReserveResDTO;
import com.raptarior.studyroomproj.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping
    private ResponseEntity<Void> createReserve(ReserveInfoReqDTO reserveInfoReqDto) {
         Long reserveId = reserveService.createReserve(reserveInfoReqDto);
         URI location = URI.create("/reservation/" + reserveId);
         return ResponseEntity.created(location).build();
    }

    @GetMapping("/{reserveId}")
    private ResponseEntity<ReserveResDTO> getReservation(@PathVariable ReserveOtherReqDTO.GetReserve reserveId) {
        ReserveResDTO reserveResDTO = reserveService.getReservation(reserveId);
        return ResponseEntity.ok(reserveResDTO);
    }

    @GetMapping("/{memberId}")
    private ResponseEntity<List<ReserveResDTO>> getReservationList(@PathVariable ReserveOtherReqDTO.GetMyReserveList memberId) {
        List<ReserveResDTO> reserveList = reserveService.getReservationList(memberId);
        return ResponseEntity.ok(reserveList);
    }

    @GetMapping
    private ResponseEntity<List<Long>> getEntireRoomStatus() {
        List<Long> roomList = reserveService.getEntireRoomStatus();
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/{roomNo}")
    private ResponseEntity<ReserveResDTO> getAvailableTimeFromRoom(@PathVariable ReserveOtherReqDTO.GetRoom roomNo) {
        ReserveResDTO reserveResDTO = reserveService.getAvailableTimeFromRoom(roomNo);
        return ResponseEntity.ok(reserveResDTO);
    }

    @GetMapping
    private ResponseEntity<List<Long>> getAvailableRoomListFromTime(ReserveOtherReqDTO.GetRoomListFromTime time) {
        List<Long> roomList = reserveService.getAvailableRoomListFromTime(time);
        return ResponseEntity.ok(roomList);
    }
}
