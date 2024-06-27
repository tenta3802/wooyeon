package com.wooyeon.yeon.chat.controller;

import com.wooyeon.yeon.chat.dto.StompDto;
import com.wooyeon.yeon.chat.dto.StompResDto;
import com.wooyeon.yeon.chat.service.ChatService;
import com.wooyeon.yeon.chat.service.StompService;
import com.wooyeon.yeon.common.fcm.dto.FcmDto;
import com.wooyeon.yeon.common.fcm.service.FcmService;
import com.wooyeon.yeon.exception.ExceptionCode;
import com.wooyeon.yeon.exception.WooyeonException;
import com.wooyeon.yeon.profileChoice.repository.MatchRepository;
import com.wooyeon.yeon.user.domain.User;
import com.wooyeon.yeon.user.repository.UserRepository;
import com.wooyeon.yeon.user.service.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StompController {
    private final StompService stompService;

    /*
        /queue/chat/room/{matchId}    - 채팅방 메시지 URL
        /app/chat/message             - 메시지 발생 이벤트 URL
        /app/unsubscribe              - 구독 취소 URL
    */

    @MessageMapping("/chat/message")
    public void enter(StompDto stompDto, @Header("Authorization") String token) {
        stompService.processSendMessage(stompDto, token);
    }
}