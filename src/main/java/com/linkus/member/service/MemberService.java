package com.linkus.member.service;

import com.linkus.member.dto.LoginMemberDto;
import com.linkus.member.dto.MemberDto;
import com.linkus.member.entity.Member;
import com.linkus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(LoginMemberDto dto) {
        System.out.println("MemberService.join() 실행");

        Member member = new Member();
        member.setName(dto.getName());
        member.setPassword(dto.getPassword());

        memberRepository.save(member);
        return member.getId();
    }

    public MemberDto login(String name, String password) {
        List<Member> members = memberRepository.findByName(name);
        for (Member member : members) {
            if(member.getPassword().equals(password)) {
                MemberDto dto = new MemberDto();
                dto.setId(member.getId());
                dto.setName(member.getName());
                dto.setPassword(member.getPassword());
                return dto;
            }
        }
        return null;
    }

}
