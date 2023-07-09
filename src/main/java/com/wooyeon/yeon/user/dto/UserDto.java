package com.wooyeon.yeon.user.dto;

import java.util.List;

public class UserDto {
    private Long userId;
    private String phone;
    private String gender;
    private String nickname;
    private List<String> profilePhoto;
    private String birthday;
    private String email;
    // 사용자가 직접 설정한 위치
    private String locationInfo;
    // 사용자의 gps를 기반으로 받아 온 실시간 위치(현재 사용자의 위치)
    private String gpsLocationInfo;
    private String mbti;
    private String intro;
    private List<String> hobby;
    private List<String> interest;

}