package com.linkus.member.entity;

import com.linkus.room.entity.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name; // 로그인 아이디 역할

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    // 연관관계 편의 메소드
    public void joinRoom(Room room) {
        if(this.room != null) {
            this.room.getMembers().remove(this);
        }
        this.room = room;
        room.setMemberNum(room.getMemberNum() + 1);
        room.getMembers().add(this);
    }

    public void leaveRoom() {
        if(this.room != null) {
            this.room.getMembers().remove(this);
            this.room.setMemberNum(this.room.getMemberNum() - 1);
        }
        this.room = null;
    }

}
