package com.linkus.member.controller;

import com.linkus.common.SessionConst;
import com.linkus.member.dto.LoginMemberDto;
import com.linkus.member.dto.MemberDto;
import com.linkus.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/add")
    public String addMemberForm(@ModelAttribute("member") LoginMemberDto loginMemberDto) {
        return "members/addMemberForm";
    }

    @PostMapping("/members/add")
    public String join(@Valid @ModelAttribute("member") LoginMemberDto loginMemberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        memberService.join(loginMemberDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("member") LoginMemberDto loginMemberDto) {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("member") LoginMemberDto loginMemberDto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }

        MemberDto memberDto = memberService.login(loginMemberDto.getName(), loginMemberDto.getPassword());

        if (memberDto == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, memberDto);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}