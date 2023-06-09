package com.linkus.room.service;

import com.linkus.member.entity.Member;
import com.linkus.member.repository.MemberRepository;
import com.linkus.room.dto.AddRoomDto;
import com.linkus.room.entity.Room;
import com.linkus.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void makeRoom(AddRoomDto dto) {
        Room room = new Room();
        room.setRoomName(dto.getRoomName());
        room.setMemberNum(0);

        roomRepository.save(room);
    }

    public List<Room> findRooms() {
        return roomRepository.findAll();
    }

    public Room findRoom(Long roomId) {
        return  roomRepository.findById(roomId);
    }

    public Room findRoom(String name) {
        return roomRepository.findByName(name);
    }

    @Transactional
    public void joinRoom(Long memberId, Long roomId) {
        Member member = memberRepository.findById(memberId);
        Room room = roomRepository.findById(roomId);

        room.addMember(member);
    }

    @Transactional
    public void leaveRoom(Long memberId) {
        Member member = memberRepository.findById(memberId);
        member.leaveRoom();
    }

    @Transactional
    public void updateRoom(Long roomId, String roomName) {
        Room room = roomRepository.findById(roomId);
        room.setRoomName(roomName);
    }
}
