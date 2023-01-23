# Spring

## 빌드 & 실행
1. cmd
2. 프로젝트 폴더에서 `gradlew.bat` 실행 = `gradlew` 입력
3. `gradlew build` 입력
4. `cd build/libs` 확인하면 `hello-spring-0.0.1-SNAPSHOT.jar` 파일 존재
5. `java -jar hello-spring-0.0.1-SNAPSHOT.jar` 입력
6. 실행 완료

## H2 데이터베이스 연결
1. `http://h2database.com/html/download-archive.html` - 1.4.200 버전
2. h2.bat 실행
3. `C:\Users\User`에서 test.mv.db 파일 생성 확인
4. build.gradle 파일에 jdbc, h2 데이터베이스 관련 라이브러리 추가  ==> [jdbc]
5. resources/application.properties 에 스프링부트 데이터베이스 연결 설정 추가
6. build.gradle 파일에 jpa 라이브러리 추가 ==> [jpa]
7. resources/application.properties 에 JPA 설정 추가