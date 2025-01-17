package com.wooyeon.yeon.chat.dto;


import com.wooyeon.yeon.chat.domain.Chat;
import com.wooyeon.yeon.user.domain.Profile;
import com.wooyeon.yeon.user.domain.ProfilePhoto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public class RoomDto {

    @Getter
    @Builder
    public static class RoomResponse {
        private List<ChatResponse> chatRoomList;
    }

    @Getter
    @Builder
    public static class ChatResponse {
        private Long matchId;
        private String profilePhoto;
        private String name;
        private LocalDateTime lastTime;
        private String lastMessage;
        private boolean pinToTop;
        private Long unReadChatCount;
    }

    @Getter
    public static class SearchRoomRequest {
        @NonNull
        private String SearchWord;
    }

    @Getter
    @Builder
    public static class SearchRoomResponse {
        private Long matchId;
        private String profilePhoto;
        private String name;
    }

    public static ChatResponse updateChatInfo(ChatResponse response, Chat chat) {
        response.lastMessage = chat.getMessage();
        response.lastTime = chat.getSendTime();

        return response;
    }

    public static ChatResponse updateProfilePhoto(ChatResponse response, ProfilePhoto profilePhoto) {
        response.profilePhoto = profilePhoto.getPhotoUrl();
        return response;
    }

    public static SearchRoomResponse updateProfilePhoto(SearchRoomResponse response, ProfilePhoto profilePhoto) {
        response.profilePhoto = profilePhoto.getPhotoUrl();
        return response;
    }

    public static SearchRoomResponse updateProfile(SearchRoomResponse response, Profile profile) {
        response.name = profile.getNickname();
        return response;
    }

    public static ChatResponse updateProfile(ChatResponse response, Profile profile) {
        response.name = profile.getNickname();
        return response;
    }
}
