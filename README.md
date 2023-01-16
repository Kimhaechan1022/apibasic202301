# 스프링 부트 프로젝트 설정
1. [프로젝트 생성 링크](https://start.spring.io)
2. HELP.md -> README.md로 이름변경, gitignore 에서 해당 파일 제거
3. application.properties -> application.yml로 이름 변경
4. yml 설정으로 톰캣 포트 변경하기


```
server:
  port: 8181
```
5. devTools 설정
- 파일 -> 설정 -> 빌드,실행, 배포 -> 컴파일러 -> 프로젝트 자동빌드 체크
- 파일 -> 설정 -> 고급설정 -> 컴파일러 -> 개발된 애플리케이션 ~~~ auto-make 체크


## 로그 레벨(중요도) 정리
1. trace : 제일 안중요함, 코드를 추적할때, 기능적 부분을 찾을때 사용
2. debug : 말그대로 디버그할때, 도움이되는 정보
3. info : 표준 로그 레벨, 서비스 시작이나 종료
4. warm : 에러는 아닌데 좀 수상한 정보를 찍는 경고
5. error : 애플리케이션이 작동하지 않는 수준의 정보( exception etc...)
6. fatal : 심각한 오류 장애수준

### JDBC API

- 스프링 JDBC 중복코드 -> SQL 매핑 : MyBatis
- 아직도 sql에 종속적이다 라는 단점이 있다. (완전한 객체 지향형 코드가 나오지 않음)
- 내가 자바 개발자인지 sql 노가다꾼인지 모를정도였다.
- 그래서 자바 코드로만 sql을 처리하는게 hibernate -> 이게 스프링에서 쓴게 jpa이다.