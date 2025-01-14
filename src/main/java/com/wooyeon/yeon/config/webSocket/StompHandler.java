package com.wooyeon.yeon.config.webSocket;

import com.wooyeon.yeon.exception.ExceptionCode;
import com.wooyeon.yeon.user.service.auth.JwtTokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        accessor.getAck();
        if (accessor.getCommand() == StompCommand.SEND || accessor.getCommand() == StompCommand.CONNECT) {
            List<String> list = accessor.getNativeHeader("Authorization");
            String bearerToken = list.get(0);
            String accessToken = null;
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
                accessToken = bearerToken.substring(7);
            }
            if (!jwtTokenProvider.validateToken(accessToken)) {
                throw new JwtException(ExceptionCode.AUTHORIZATION_FAILED.toString());
            }
            // 토큰이 유효한 경우, 사용자 정보 추출
            Authentication loginAuthentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(loginAuthentication);
        }
        return message;
    }

    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        if (null != accessor.getCommand()) {
            switch ((accessor.getCommand())) {
                case CONNECT:
                    // 유저가 Websocket으로 connect()를 한 뒤 호출됨
                    log.info("세션 들어옴 => {}", sessionId);
                    break;
                case DISCONNECT:
                    // 유저가 Websocket으로 disconnect() 를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생
                    log.info("세션 끊음 => {}", sessionId);
                    break;
                default:
                    break;
            }
        }
    }
}
