package com.raptarior.studyroomproj.service;

import com.raptarior.studyroomproj.common.ReserveStatus;
import com.raptarior.studyroomproj.dto.ReserveInfoReqDTO;
import com.raptarior.studyroomproj.dto.ReserveMapper;
import com.raptarior.studyroomproj.dto.ReserveOtherReqDTO;
import com.raptarior.studyroomproj.dto.ReserveResDTO;
import com.raptarior.studyroomproj.entity.Member;
import com.raptarior.studyroomproj.entity.Reservation;
import com.raptarior.studyroomproj.entity.ReservationSubject;
import com.raptarior.studyroomproj.repository.MemberRepository;
import com.raptarior.studyroomproj.repository.ReserveSubjectRepository;
import com.raptarior.studyroomproj.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private final ReserveSubjectRepository reserveSubjectRepository;

    public Long createReservation(ReserveInfoReqDTO req) {
        Reservation reservation = reserveMapper.infoReqDtoToEntity(req);
        Member member = memberRepository.findById(req.getMemberId()).orElseThrow();
        reservation.setMember(member);
        List<ReservationSubject> reservationSubjects = reserveSubjectRepository.findAllById(req.getReservationSubjectIds());
        reservation.setReservationSubjects(reservationSubjects);

        Reservation result = reserveRepository.save(reservation);
        return result.getId();
    }

    @Transactional(readOnly = true)
    public ReserveResDTO getReservation(Long reserveId) {
        Reservation reservation = reserveRepository.findById(reserveId).orElseThrow();
        ReserveResDTO reserveResDTO = reserveMapper.entityToResDto(reservation);
        return reserveResDTO;
    }

    @Transactional(readOnly = true)
    public List<ReserveResDTO> getReservationList(Long memberId) {
        List<Reservation> reservationList = reserveRepository.findByMemberId(memberId);
        List<ReserveResDTO> reserveResDTOList = new ArrayList<>();

        for (Reservation rsv : reservationList) {
            ReserveResDTO rsvDto = reserveMapper.entityToResDto(rsv);
            reserveResDTOList.add(rsvDto);
        }

        return reserveResDTOList;
    }

    public List<Long> getEmptyRoomList() {
        List<Long> usingRoomList = reserveRepository.findByStatus(ReserveStatus.USING).stream()
                .map(rsv -> rsv.getRoomNo()).toList();

        Set<Long> usingRooms = new HashSet<>(usingRoomList);

        List<Long> emptyRoomList = LongStream.range(0, 100)
                .filter(n -> !usingRooms.contains(n)).boxed().toList();

        return emptyRoomList;
    }

    /**
     * 가능한 시간을 반환 (ex. 13 -> 13:00~14:00)
     */
    public List<Integer> getAvailableTimeFromRoom(Long roomNo) {
        Set<Integer> usingTimes = new HashSet<>();
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime tomorrow = today.plusDays(1);

        reserveRepository.findByRoomNoInToday(roomNo, today, tomorrow).stream()
                .forEach(rsv -> {
                    for (int i = rsv.getStartTime().getHour(); i <= rsv.getEndTime().getHour(); i++) {
                        usingTimes.add(i);
                    }
                });
        List<Integer> availableTimeList = IntStream.range(0, 24)
                .filter(n -> !usingTimes.contains(n)).boxed().toList();
        return availableTimeList;
    }

    /**
     * 해당 시간으로부터 1시간 내에 예약이 없는 방들을 반환
     */
    public List<Long> getAvailableRoomListFromTime(ReserveOtherReqDTO.GetRoomList req) {
        LocalDateTime plusOneHour = req.time().plusHours(1);

        List<Long> usingRoomList = reserveRepository.findByStatusAndTime(plusOneHour).stream()
                .map(rsv -> rsv.getRoomNo()).toList();

        Set<Long> usingRooms = new HashSet<>(usingRoomList);

        List<Long> availableRoomList = LongStream.range(0, 100)
                .filter(n -> !usingRooms.contains(n)).boxed().toList();

        return availableRoomList;
    }
}
