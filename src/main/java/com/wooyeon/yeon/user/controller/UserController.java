package com.wooyeon.yeon.user.controller;

import com.wooyeon.yeon.user.dto.MemberRegisterRequestDto;
import com.wooyeon.yeon.user.dto.MemberRegisterResponseDto;
import com.wooyeon.yeon.user.service.EmailAuthService;
import com.wooyeon.yeon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailAuthService emailAuthService;

    @Autowired
    public UserController(UserService userService, EmailAuthService emailAuthService) {
        this.userService = userService;
        this.emailAuthService = emailAuthService;
    }

    @GetMapping("/auth/email"정)
    public ResponseEntity<MemberRegisterResponseDto> registerMember(String email) {
        MemberRegisterRequestDto requestDto = new MemberRegisterRequestDto();
        requestDto.setEmail(email);
        MemberRegisterResponseDto responseDto = userService.registerMember(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/auth/email/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String email, String token) {
        emailAuthService.verifyEmail(email, token);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }
}