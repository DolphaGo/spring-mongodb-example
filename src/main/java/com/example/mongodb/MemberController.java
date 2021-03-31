package com.example.mongodb;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.domain.Member;
import com.example.mongodb.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/c")
    public List<Member> c(){
        return memberService.selectAll();
    }

    @GetMapping("/r")
    public void r(){
        memberService.insert();
    }
}
