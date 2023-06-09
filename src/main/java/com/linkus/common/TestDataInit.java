package com.linkus.common;

import com.linkus.member.dto.LoginMemberDto;
import com.linkus.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        LoginMemberDto dto = new LoginMemberDto();
        dto.setName("test");
        dto.setPassword("test!");

        memberService.join(dto);
    }

}