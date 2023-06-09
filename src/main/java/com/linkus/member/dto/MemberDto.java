package com.linkus.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
