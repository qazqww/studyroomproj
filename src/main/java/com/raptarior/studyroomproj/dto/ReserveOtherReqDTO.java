package com.raptarior.studyroomproj.dto;

import com.raptarior.studyroomproj.common.ReserveStatus;

import java.time.LocalDateTime;

public class ReserveOtherReqDTO {
    public record GetRoomList(LocalDateTime time) {}
    public record TimeExpand(Long id, LocalDateTime endTime) {}
    public record CheckInOut(Long id, ReserveStatus status) {}
}
