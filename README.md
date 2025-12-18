# JWT & OAuth 기반 Todo 서비스

> React 프론트엔드와 Spring Boot 백엔드를 Docker로 컨테이너화하여 AWS EC2에 배포한 Todo 관리 서비스입니다.  
> 개인 프로젝트로 풀스택 개발 경험과 CI/CD, 배포까지 전 과정을 경험하기 위해 제작했습니다.

---

## 프로젝트 개요

- **한 줄 설명**  
  JWT & OAuth 인증 기반 Todo 관리 서비스

- **목표**  
  - React ↔ Spring Boot API 연동 경험
  - Spring Security + JWT 인증 구조 이해
  - OAuth2 로그인 경험
  - Docker / AWS 배포 경험
  - CI/CD, Kubernetes 확장 가능 구조 설계

- **개발 기간**  
  총 0일

---

## 🔧 기술 스택

| 분류          | 기술                  | 설명      |
|---------------|----------------------|-----------|
| Frontend      | React, Axios, Router | API 연동 |
| Backend       | Java 17, Spring Boot 3.x, Spring Security, JPA, JWT, OAuth2 Client | REST API, 인증/인가 |
| Database      | H2 (개발), MySQL (운영) | 영구 데이터 저장 |
| Infra / DevOps| Docker, Docker Compose, AWS EC2 | 컨테이너 환경 및 클라우드 배포 |
| API Docs      | Swagger, Postman    | 테스트 및 문서화 |


## 인증 흐름

### JWT 인증

1. 사용자가 로그인 요청  
2. 인증 성공 시 JWT 발급  
3. React에서 JWT 저장  
4. 이후 요청 시 Authorization Header에 JWT 포함  
5. Spring Security Filter에서 JWT 검증  


### OAuth2 인증

1. Google OAuth 로그인 요청  
2. OAuth 인증 성공  
3. 사용자 정보 기반 계정 생성 또는 조회  
4. JWT 발급 후 서비스 접근  


## 트러블 슈팅

- **JWT 인증 시 401 오류 발생**  
  - 원인: Authorization Header 누락  
  - 해결: Axios 인터셉터에서 JWT 자동 포함  

- **Docker 환경에서 DB 연결 실패**  
  - 원인: localhost 사용  
  - 해결: docker-compose 서비스 이름으로 변경

---

## API 문서 & 테스트



## 배포 환경

