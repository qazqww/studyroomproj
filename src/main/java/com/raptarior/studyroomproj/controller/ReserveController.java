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
    private ResponseEntity<Void> createReservation(ReserveInfoReqDTO req) {
         Long reserveId = reserveService.createReservation(req);
         URI location = URI.create("/reservation/" + reserveId);
         return ResponseEntity.created(location).build();
    }

    @GetMapping("/{reserveId}")
    private ResponseEntity<ReserveResDTO> getReservation(@PathVariable Long reserveId) {
        ReserveResDTO reserveResDTO = reserveService.getReservation(reserveId);
        return ResponseEntity.ok(reserveResDTO);
    }

    @GetMapping("/list/{memberId}")
    private ResponseEntity<List<ReserveResDTO>> getReservationList(@PathVariable Long memberId) {
        List<ReserveResDTO> reserveResDTOList = reserveService.getReservationList(memberId);
        return ResponseEntity.ok(reserveResDTOList);
    }

    @GetMapping
    private ResponseEntity<List<Long>> getEmptyRoomList() {
        List<Long> roomList = reserveService.getEmptyRoomList();
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/searchby-room/{roomNo}")
    private ResponseEntity<List<Integer>> getAvailableTimeFromRoom(@PathVariable Long roomNo) {
        List<Integer> timeList = reserveService.getAvailableTimeFromRoom(roomNo);
        return ResponseEntity.ok(timeList);
    }

    @GetMapping("/searchby-time")
    private ResponseEntity<List<Long>> getAvailableRoomListFromTime(ReserveOtherReqDTO.GetRoomList req) {
        List<Long> roomList = reserveService.getAvailableRoomListFromTime(req);
        return ResponseEntity.ok(roomList);
    }
}
