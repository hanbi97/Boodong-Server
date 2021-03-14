# 부동산 매물 관리 프로그램 - BE

👩‍💻 BE <a href="https://github.com/hanbi97/Boodong-Server">Hanbi-Kim</a> <br>
👨‍💻 FE <a href="https://github.com/JuHyeon-Lee/Boodong-Client">JuHyeon-Lee</a>

## About
📌 공인중개사의 주 업무: 매물관리 + 각 사이트 양식에 맞춰 매물 광고 올리기 + 손님 예약관리 <br>
📌 매물 정보와 손님 정보를 한번에 등록 <br>
📌 쉽고 빠른 매물 검색 가능
```
🔎 검색조건
        -- 포함 OR 일치
                -- 판매상태
                        -- 판매 중 물건
                        -- 보류 중 물건
                        -- 보류 중 물건
                -- 매물 종류
                        -- 월세
                        -- 전세
                        -- 매매
                -- 매물 관련
                         -- 매물 이름
                         -- 동
                         -- 호수                       
                -- 고객 관련
                         -- 고객 이름
                         -- 고객 핸드폰 번호
        -- 범위 검색
                -- 등록날짜                     
                -- 평
                -- 보증금               
                -- 매매가    
```


## Tech Stack
FE - React <br>
BE - Spring Boot, MariaDB, Spring Data JPA, Spring Security(JWT), AWS


## API
<a href="https://documenter.getpostman.com/view/4405431/Tz5jeKsq">
PostMan </a> 👈 click


## What I Learned
<a href="https://hanbi97.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/Boodong-BE">
비스토리 </a> 👈 click


## To Do
+[ ] 영속성 유지
+[ ] 사용자별 매물 확인 가능하도록(account pk, foreign key 이용)
+[ ] 실시간 통신 (socket, http polling의 단점, http는 왜 application layer 에서 하는지 고민해볼것)
+[ ] 일정관리 API(손님예약 관리)
+[ ] 베타테스트 하고 수정사항 반영




