# api-gateway

## 프로젝트 목표

- Microservice Architecture의 전반적인 이해 및 패턴 구현
- 추후 프로젝트에 쓸 API Gateway를 구축
- 안전한 API 유통과 Client 별 요청을 유연하게 대처

## 사용 기술 및 환경

![image](https://user-images.githubusercontent.com/73684562/175821292-6d00517f-011b-4ebb-893a-11a42622ecaa.png)

- Spring Cloud Gateway
- Spring Cloud Circuit Breaker
- Spring Cloud Eureka
- Spring Cloud Config
- JWT

## 이슈

- [Spring Cloud Gateway] 어떤 서비스로 보낼지 API URI에서 구분 필요
    - predicates 설정을 통해 uri 시작을 서비스 이름으로 하도록 하고, 서비스로 포워딩 될 때에는 path를 rewrite 하여 전송
- [JWT] 권한에 따라 API 접근이 제한될 필요 (관리자 메뉴, 회원 등급에 따른 메뉴 등)
    - JWT decoding 했을 때 id 와 role 정보를 받음 → role 에 따라 접근 가능한지 확인하는 구조
- [JWT] Gateway에서 JWT 발행 기능을 구현하면 로그인 기능을 구현해야하기 때문에 업무가 과중되는 문제
    - 로그인, JWT 발행은 user-mgmt 서비스에 구현.
    - 추후 개발 예정 서비스 로직: (Gateway 거쳐) user-mgmt API로 로그인 → JWT 토큰 발행 → Redis에 저장 → Client에 JWT 토큰 전달 → Client가 Request할 때 JWT 토큰 담아 전송 → Gateway 가 Decoding 하여 정보 확인 → Redis 정보와 비교하여 유효성 더블 체크
- [ Circuit Breaker] Histrix → Resilience4J
    - 2018년도 이후 Netflix에서 Histrix를 유지보수로만 관리한다고 발표 후 Spring Cloud Netflix도 동일, 따라서 Spring cloud와의 호환성 및 확장성을 위하여 Resilience4j로 대체
- [ Circuit Breaker] Resilience4J의 application.yml configuration 이슈
    - application.yml에서 관련 설정값을 변경하여도 반영되지 않는 이슈
    - resilience4j의 버전 문제로 추측하여 java configuration(CustomizeCircuirBreakerConfig.java)로 처리하여 정상 반영 확인
- [ Config ] Eureka config server fetching issue
    - config 설정 파일 내 eureka 설정파일 등록 시 fetch 실패 이슈 발생
    - eureka 설정파일은 디스펜서 의존 이슈로 인하여 따로 관리

## Future Work

- JWT : user-service와 Gateway에 Redis연동하여 토큰 유효성 보안 강화
- Redis 연결 : JWT 토큰 캐싱을 위한 DB연결
- Cloud연결 : 컴파일한 서비스들을 함께 실행 할 수 있도록 cloud 연결

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

## Microservices

- Spring Service : [https://github.com/smellHyang/eureka-client.git](https://github.com/smellHyang/eureka-client.git)
- User Service : [https://github.com/dayoungMM/login-Oauth2.git](https://github.com/dayoungMM/login-Oauth2.git)
- Yanolja Service : [https://github.com/f-lab-edu/yanolja-clone.git](https://github.com/f-lab-edu/yanolja-clone.git)
- Eureka Server : [https://github.com/smellHyang/eureka-server.git](https://github.com/smellHyang/eureka-server.git)
- Config Server : [https://github.com/smellHyang/config-server.git](https://github.com/smellHyang/config-server.git)
- Config git : [https://github.com/smellHyang/msa-config.git](https://github.com/smellHyang/msa-config.git)
