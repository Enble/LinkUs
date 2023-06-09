package com.linkus.room.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditRoomDto {

    private long id;

    @NotBlank
    private String roomName;

}
