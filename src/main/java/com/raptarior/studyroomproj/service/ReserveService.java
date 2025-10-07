package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.dto.ReserveInfoReqDTO;
import com.raptarior.studyroomproj.dto.ReserveMapper;
import com.raptarior.studyroomproj.dto.ReserveOtherReqDTO;
import com.raptarior.studyroomproj.dto.ReserveResDTO;
import com.raptarior.studyroomproj.entity.Reservation;
import com.raptarior.studyroomproj.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;

    public Long createReserve(ReserveInfoReqDTO reserveInfoReqDto) {
        Reservation reservation = reserveMapper.infoReqDtoToEntity(reserveInfoReqDto);
        Reservation result = reserveRepository.save(reservation);
        return result.getId();
    }

    public ReserveResDTO getReservation(ReserveOtherReqDTO.GetReserve reserveId) {
        Reservation reservation = reserveRepository.findById(reserveId.id()).orElseThrow();
        ReserveResDTO reserveResDTO = reserveMapper.entityToResDto(reservation);
        return reserveResDTO;
    }

    public List<ReserveResDTO> getReservationList(ReserveOtherReqDTO.GetMyReserveList memberId) {
        List<Reservation> reservationList = reserveRepository.findByMemberId(memberId.memberId());
        List<ReserveResDTO> reserveResDTOList = reserveMapper.entityToResDtoList(reservationList);
        return reserveResDTOList;
    }

    public List<Long> getEntireRoomStatus() {
//        reserveRepository.findAll().stream().map();
        LocalDateTime nowTime = LocalDateTime.now();
        // 취소되지 않은 예약 중 (시작 시간 < 현재 시간) & (현재 시간 < 마감 시간) 이 없는 방들을 리턴
        return null;
    }

    public ReserveResDTO getAvailableTimeFromRoom(ReserveOtherReqDTO.GetRoom roomNo) {
        // 해당 room의 가능한 시간 단위들 (ex. 13:00~14:00 비어있으면 13) 리스트로 리턴
        return null;
    }

    public List<Long> getAvailableRoomListFromTime(ReserveOtherReqDTO.GetRoomListFromTime time) {
        // 현재 시간으로부터 1시간 후까지 예약이 없는 방들 리턴
        return null;
    }
}
