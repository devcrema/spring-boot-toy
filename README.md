# spring-boot-toy

간단한 음식점 도메인 예제

구현기능

- 회원가입

swagger
http://localhost:8080/swagger-ui.html

#### mysql 5.7 

create database spring_boot_toy DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci; #디비 생성

create user 'spring_boot_toy_admin'@'localhost' identified by 'spring_boot_toy_pw1'; #유저 생성

GRANT ALL PRIVILEGES ON spring_boot_toy.* TO 'spring_boot_toy_admin'@'localhost' identified by 'spring_boot_toy_pw1';#권한 설정