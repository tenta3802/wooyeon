package com.wooyeon.yeon.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile")
    private User user;

    @Column(nullable = false)
    private char gender;

    @Column(length = 2)
    private int age;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column
    @OneToMany(mappedBy = "profile")
    private List<ProfilePhoto> profilePhotos = new ArrayList<>();

    @Column(length = 8, nullable = false)
    private String birthday;

    @Column(length = 100, nullable = false)
    private String locationInfo;

    @Column(length = 100, nullable = false)
    private String gpsLocationInfo;

    @Column(length = 4)
    private String mbti;

    @Column(length = 50)
    private String intro;

    @Column(length = 100)
    private String hobby;

    @Column(length = 100)
    private String interest;

    private boolean faceVerify;

    @Builder
    public Profile(char gender, String nickname, int age, List<ProfilePhoto> profilePhotos, String birthday, String locationInfo, String gpsLocationInfo, String mbti, String intro, String hobby, String interest, boolean faceVerify) {
        this.gender = gender;
        this.nickname = nickname;
        this.age = age;
        this.profilePhotos = profilePhotos;
        this.birthday = birthday;
        this.locationInfo = locationInfo;
        this.gpsLocationInfo = gpsLocationInfo;
        this.mbti = mbti;
        this.intro = intro;
        this.hobby = hobby;
        this.interest = interest;
        this.faceVerify = faceVerify;
    }
}