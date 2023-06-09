package com.linkus.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginMemberDto {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
