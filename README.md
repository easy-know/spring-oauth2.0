# OAuth2.0 Server 구축 Release 1
> Spring 기반 인증 서버 구축

## 모듈 구성
1. common 
   - 공통 모듈

2. domain-rds
   - 관계형 DB(h2) 관련 모듈

3. oauth-app-auth-server
   - 인증 서버 관련 모듈
   - port number: `8090`

4. oauth-app-client 
   - Client 모듈 (ex_Career System)
   - port number: `8086`

5. oauth-app-developer 
   - OAuth2 Server 설정 화면(Developer 페이지)
   - port number: `8080`

6. oauth-app-resource-server 
   - 리소스 서버 관련 모듈(Client에서 Resource Server로 자원 및 권한 요청)
   - port number: `8084`