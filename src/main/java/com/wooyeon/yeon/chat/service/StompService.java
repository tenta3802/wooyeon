package com.wooyeon.yeon.chat.service;

import com.wooyeon.yeon.chat.dto.StompDto;
import com.wooyeon.yeon.chat.dto.StompResDto;
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
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class StompService {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatService chatService;
    private final FcmService fcmService;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static Map<String, String> sessionStore = new ConcurrentHashMap<>();

    public void processSendMessage(StompDto stompDto, String token) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token.substring(7));
        String loginEmail = authentication.getName();
        Long roomId = stompDto.getRoomId();

        if (stompDto.getType().equals(StompDto.MessageType.ENTER.toString())) {
            plusSessionCount(roomId);
        }
        if (stompDto.getType().equals(StompDto.MessageType.TALK.toString())) {
            sendMessage(loginEmail, stompDto);
        }
        if (stompDto.getType().equals(StompDto.MessageType.QUIT.toString())) {
            minusSessionCount(roomId);
        }
        if (stompDto.getType().equals(StompDto.MessageType.TALK.toString()) &&
                "1".equals(sessionStore.get(roomId.toString()))) {
            sendFcmMessage(roomId, stompDto, loginEmail);
        }
    }

    private void plusSessionCount(Long roomId) {
        if (!sessionStore.containsKey(roomId.toString()) || "0".equals(sessionStore.get(roomId.toString()))) {
            sessionStore.put(roomId.toString(), "1");
        } else if ("1".equals(sessionStore.get(roomId.toString()))) {
            sessionStore.put(roomId.toString(), "2");
        }
        log.info("session count = " + sessionStore.get(roomId.toString()));
    }

    private void sendMessage(String loginEmail, StompDto stompDto) {
        User loginUser = userRepository.findOptionalByEmail(loginEmail)
                .orElseThrow(() -> new WooyeonException(ExceptionCode.LOGIN_USER_NOT_FOUND));

        StompResDto stompRes = StompResDto.builder()
                .message(stompDto.getMessage())
                .sendTime(LocalDateTime.now())
                .senderToken(loginUser.getAccessToken())
                .build();

        simpMessageSendingOperations.convertAndSend("/queue/chat/room/" + stompDto.getRoomId(), stompRes);

        chatService.saveChat(stompDto, sessionStore, loginEmail);
        log.info("채팅 전송 완료");
    }

    private void minusSessionCount(Long roomId) {
        int count = Integer.parseInt(sessionStore.get(roomId.toString()));
        sessionStore.put(roomId.toString(), String.valueOf(--count));

        log.info("session count = " + sessionStore.get(roomId.toString()));
    }

    private void sendFcmMessage(Long roomId, StompDto stompDto, String loginEmail) {
        log.info("session count = " + sessionStore.get(roomId.toString()));
        try {
            fcmService.sendMessageTo(FcmDto.buildRequest(loginEmail, stompDto, roomId, userRepository, matchRepository));
            log.info("FCM 메시지 전송함");
        } catch (IOException e) {
            throw new WooyeonException(ExceptionCode.FCM_SEND_FAIL_ERROR);
        }
    }
}
