package com.wooyeon.yeon.likeManage.dto;

import lombok.Data;

import java.util.UUID;

/**
 * @author heesoo
 */
@Data
public class RequestLikeRequestDto {
    private UUID likeToUserUUID;
    private UUID likeFromUserUUID;
}
