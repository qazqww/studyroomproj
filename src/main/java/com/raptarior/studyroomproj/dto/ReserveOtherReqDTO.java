package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;

import java.time.LocalDateTime;

public class ReserveOtherReqDTO {
    public record GetReserve(Long id) {}
    public record GetMyReserveList(Long memberId) {}
    public record GetRoom(Long roomNo) {}
    public record GetRoomListFromTime(LocalDateTime time) {}
    public record TimeExpand(Long id, LocalDateTime endTime) {}
    public record CheckInOut(Long id, ReserveStatus status) {}
}
