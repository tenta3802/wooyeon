package com.wooyeon.yeon.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StompDto {
    private Long roomId;
    private String message;
    private String type;

    public enum MessageType {
        ENTER, QUIT, TALK
    }
}