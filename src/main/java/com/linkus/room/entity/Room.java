package com.linkus.room.entity;

import com.linkus.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private String roomName;

    private int memberNum;

    @OneToMany(mappedBy = "room")
    private List<Member> members = new ArrayList<>();

    // 연관관계 편의 메소드
    public void addMember(Member member) {
        if(member.getRoom() != this) {
            member.joinRoom(this);
        }
    }

    public void removeMember(Member member) {
        if(member.getRoom() == this) {
            member.leaveRoom();
        }
    }

}
