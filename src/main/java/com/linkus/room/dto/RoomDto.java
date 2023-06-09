package com.linkus.room.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private long id;

    @NotBlank
    private String roomName;

    private int memberNum;

}
