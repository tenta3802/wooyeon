
## 👩🏻‍❤️‍👨🏻우연 (Wooyeon) 
> 소셜 디스커버리 애플리케이션

</br>

### 📖 Description
앱 사용자는 자신의 지역을 설정하면 이후 같은 지역에 있는 다른 사용자의 목록을 확인할 수 있습니다. 또한 목록의 사용자 중</br>
궁금한 사용자의 프로필을 클릭하여 상세 정보를 확인할 수 있습니다. 만약 프로필 정보 확인 후 상대방과 친해지기를 원하면 좋아요를</br>
뜻하는 오른쪽 방향으로 화면을 넘깁니다. 그 후 상대방도 좋아요를 하게 되면 매칭이 되어 채팅이 가능하게 됩니다.

</br>

### :baby_chick: Demo
<p float="left">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 01 07" src="https://github.com/tenta3802/wooyeon/assets/84509774/6f4570e2-79cb-4d1b-8884-bab462f7dd2c">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 26 41" src="https://github.com/tenta3802/wooyeon/assets/84509774/e044c824-53fb-4251-9f61-0f6e8340fe45">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 28 19" src="https://github.com/tenta3802/wooyeon/assets/84509774/1a643fa7-4dcf-41e9-a636-b0de727dcdee">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 27 31" src="https://github.com/tenta3802/wooyeon/assets/84509774/acb0a8f8-3d3e-4b9a-8ead-0be183c1ddde">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 29 44" src="https://github.com/tenta3802/wooyeon/assets/84509774/fb4666e7-830f-4540-85b6-be646a35f7e8">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 02 51" src="https://github.com/tenta3802/wooyeon/assets/84509774/58c7441d-710b-4eaa-b0d3-1efa3386f07f">
    <img width="200" alt="스크린샷 2024-05-06 오후 9 03 08" src="https://github.com/tenta3802/wooyeon/assets/84509774/991e7481-3034-4957-ade3-a27ed0f6ae90">
</p>

</br>

### ⭐ My Implementation
#### 회원 인증 
- SpringSecurity, JWT를 이용한 회원 인증 및 인가 기능 구현
- JWT Authentication Filter를 통한 회원 검증 로직 구현
#### 채팅 기능
- Spring Boot STOMP(Simple Text Oriented Messaging Protocol)를 통한 실시간 채팅 기능 구현
- FCM 통한 실시간 채팅 알림 기능 구현
- REST API 외 STOMP, FCM을 통해 이뤄지는 모든 통신 과정에 JWT 회원 인증 적용
#### 공통 기능
- Global Exception 처리를 통한 공통 예외 처리 구현
- 공통 Response 객체 포맷 적용

</br>

### 🔧 Server Stack
- **Language**: Java 11
- **Framework** : Spring boot 2.7.13
- **BuildTool** : Gradle 7.6.1
- **Database** : AWS RDS(MySQL)
- **ORM** : JPA(Hibernate)

</br>

### 🗂️ ERD
<p float="left">
    <img width="804" alt="스크린샷 2024-07-14 오후 9 40 56" src="https://github.com/user-attachments/assets/551013ba-6368-4b7a-831b-c5c0e4b44db6">
</p>

</br>

### ⚒ CI/CD
-  Docker & Jenkins를 활용한 지속적 통합 및 배포
-  각각의 `feature` 브랜치에서 `develop` 브랜치로 Pull Request 후 Merge 하면 CI 동작
-  CI 완료 후 Jenkins를 통해 운영 리소스에 배포

</br>

### ✏️ Commit Covention
- `FEAT` : 새로운 기능 추가 </br>
- `FIX` : 버그 수정 </br>
- `DOCS` : 문서 수정 </br>
- `STYLE` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 </br>
- `REFCTOR` : 코드 리펙토링 </br>
- `TEST` : 테스트 코드, 리펙토링 테스트 코드 추가 </br>
- `BUILD` : 빌드 파일 및 관련 업무 수정 </br>
- `CHORE` : 자잘한 수정

