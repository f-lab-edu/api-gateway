# api-gateway

## 프로젝트 목표

- 추후 프로젝트에 쓸 API Gateway를 구축
- 안전한 API 유통과 Client 별 요청을 유연하게 대처

## 사용 기술 및 환경

- Spring Cloud Gateway
- Eureka
- JWT
- Spring Cloud Config

## 이슈

- [Spring Cloud Gateway] 어떤 서비스로 보낼지 API URI에서 구분 필요
    - predicates 설정을 통해 uri 시작을 서비스 이름으로 하도록 하고, 서비스로 포워딩 될 때에는 path를 rewrite 하여 전송
- [JWT] 권한에 따라 API 접근이 제한될 필요 (관리자 메뉴, 회원 등급에 따른 메뉴 등)
    - JWT decoding 했을 때 id 와 role 정보를 받음 → role 에 따라 접근 가능한지 확인하는 구조
- [JWT] Gateway에서 JWT 발행 기능을 구현하면 로그인 기능을 구현해야하기 때문에 업무가 과중되는 문제
    - 로그인, JWT 발행은 user-mgmt 서비스에 구현.
    - 추후 개발 예정 서비스 로직: (Gateway 거쳐) user-mgmt API로 로그인 → JWT 토큰 발행 → Redis에 저장 → Client에 JWT 토큰 전달 → Client가 Request할 때 JWT 토큰 담아 전송 → Gateway 가 Decoding 하여 정보 확인 → Redis 정보와 비교하여 유효성 더블 체크

## **브랜치 관리 전략**

- Issue 생성 → Issue 번호를 딴 branch 생성
- 목적에 따라 feat, refact, debug 폴더로 branch 관리

```java
keyword
- feat : 새로운 기능이 추가 됨
- refact : 기능 혹은 성능 개선
- debug : 버그 수정
```
- 개발 완료 후 main branch 내용을 current branch로 Merge한 후 Pull Request
