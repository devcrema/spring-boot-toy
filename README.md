# spring-boot-toy

간단한 음식점 도메인 예제

구현기능

| TODO | Done? | comment |
|------|:-----:|---------|
| 유저 구조 생성 | O |  |
| 유저/요리사 로그인 | O | oauth2 + spring security |
| 유저/요리사 회원가입 | O |  |
| 음식점 리스트 + 페이징| O| |
| 음식점 정렬 (랭킹순, 리뷰개수순, 평점순)| | |
| 음식점 필터 (음식점 카테고리)| | |
| 예약(유저),1시간 단위 | | |
| 예약 수락 (요리사)| | |
| 예약 거절 (요리사)| | |
| 예약 수락을 20분 이상 하지 않을 시, 자동 거절| | |
| 예약 취소 (유저)| | |
| 리뷰 쓰기/수정 (유저)| | |
| 리뷰 리스트 (리스트 페이징)| | |
| 리뷰 추천 (유저)| | |
| 리뷰 댓글 (유저, 요리사)| | |
| 요리사 추천| | |
| 유저 랭킹 (리뷰개수,추천)| | |
| 요리사 랭킹 (리뷰개수,추천)| | |

swagger
http://localhost:8080/swagger-ui.html

#### mysql 5.7 

create database spring_boot_toy DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; #디비 생성

create user 'spring_boot_toy_admin'@'localhost' identified by 'spring_boot_toy_pw1'; #유저 생성

GRANT ALL PRIVILEGES ON spring_boot_toy.* TO 'spring_boot_toy_admin'@'localhost' identified by 'spring_boot_toy_pw1';#권한 설정