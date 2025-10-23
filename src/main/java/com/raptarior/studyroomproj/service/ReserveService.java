package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.dto.ReserveInfoReqDTO;
import com.raptarior.studyroomproj.dto.ReserveMapper;
import com.raptarior.studyroomproj.dto.ReserveOtherReqDTO;
import com.raptarior.studyroomproj.dto.ReserveResDTO;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Reservation;
import com.raptarior.studyroomproj.repository.MemberRepository;
import com.raptarior.studyroomproj.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RequiredArgsConstructor
@Service
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;
    private final MemberRepository memberRepository;

    public Long createReservation(ReserveInfoReqDTO reserveInfoReqDto) {
        Reservation reservation = reserveMapper.infoReqDtoToEntity(reserveInfoReqDto);
        Member member = memberRepository.findById(reserveInfoReqDto.getMemberId()).orElseThrow();
        reservation.setMember(member);

        Reservation result = reserveRepository.save(reservation);
        return result.getId();
    }

    @Transactional(readOnly = true)
    public ReserveResDTO getReservation(ReserveOtherReqDTO.GetReserve reserveId) {
        Reservation reservation = reserveRepository.findById(reserveId.id()).orElseThrow();
        ReserveResDTO reserveResDTO = reserveMapper.entityToResDto(reservation);
        return reserveResDTO;
    }

    @Transactional(readOnly = true)
    public List<ReserveResDTO> getReservationList(ReserveOtherReqDTO.GetMyReserveList memberId) {
        List<Reservation> reservationList = reserveRepository.findByMemberId(memberId.memberId());
        List<ReserveResDTO> reserveResDTOList = reserveMapper.entityToResDtoList(reservationList);
        return reserveResDTOList;
    }

    public List<Long> getEmptyRoomList() {
        List<Long> roomList = reserveRepository.findAll().stream().filter(rsv ->
            rsv.getStatus() != ReserveStatus.USING
        ).map(rsv -> rsv.getRoomNo()).toList();
        return roomList;
    }

    /**
     * 가능한 시간을 반환 (ex. 13 -> 13:00~14:00)
     */
    public List<Integer> getAvailableTimeFromRoom(ReserveOtherReqDTO.GetRoom roomNo) {
        Set<Integer> usingTimes = new HashSet<>();
        reserveRepository.findByRoomNo(roomNo.roomNo()).stream().forEach(rsv -> {
            usingTimes.add(rsv.getStartTime().getHour());
            usingTimes.add(rsv.getEndTime().getHour());
        });
        List<Integer> availableTimeList = IntStream.range(0, 24)
                .filter(n -> !usingTimes.contains(n)).boxed().toList();
        return availableTimeList;
    }

    /**
     * 현재 시간으로부터 1시간 내에 예약이 없는 방들을 반환
     */
    public List<Long> getAvailableRoomListFromTime(ReserveOtherReqDTO.GetRoomListFromTime time) {
        List<Long> usingRoomList = reserveRepository.findAll().stream().filter(rsv ->
                rsv.getStatus() == ReserveStatus.RESERVED &&
                        rsv.getStartTime().isBefore(LocalDateTime.now().plusHours(1))
        ).map(rsv -> rsv.getRoomNo()).toList();
        Set<Long> usingRooms = new HashSet<>(usingRoomList);

        List<Long> availableRoomList = LongStream.range(0, 100)
                .filter(n -> !usingRooms.contains(n)).boxed().toList();

        return availableRoomList;
    }
}
