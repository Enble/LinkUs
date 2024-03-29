package com.linkus.room.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRoomDto {

    @NotBlank
    private String roomName;
}
