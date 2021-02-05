# 프로젝트 설명

## 소개

인텔리제이 - New Project Gradle 로 생성

- 언어: `Java:1.8`
- 프레임워크: `Spring boot:2.4.2`
- 빌드 툴: `Gradle`
- IDE : `InteliJ`, (git 은 `SourceTree` 와 혼용하여 사용)

사용한 의존성 정리

- `spring-boot-start-web` : Spring MVC
- `spring-session-jdbc` : Spring Session (JDBC 사용)
- `spring-boot-starter-data-jpa` : Spring JPA
- `querydsl-jpa` : QUERYDSL
- `hibernate-validator` : Bean Validator
- `springfox-boot-starter` : Swagger
- `lombok` : Lombok
- `mysql-connector-java` : Mysql
- `h2` : H2
- `spring-boot-devtools` : Devtools
- `spring-boot-starter-test` : Test (Junit5)

## 로컬 실행 방법

application-local.properties 기준

1. [init.sql](./src/main/resources/sql/init.sql) 로 테이블 생성
2. gradle task > clean, task classes (`QEntity` 생성)
3. `Application.java` 에서 `run()`

빌드는 gradle task > build

## 참고 내용

- 프로젝트 구성 전반: [spring-guide](https://github.com/cheese10yun/spring-guide)
- querydsl: [Spring Boot Data Jpa 프로젝트에 Querydsl 적용하기](https://jojoldu.tistory.com/372)
    - [querydsl 설정관련 질문드립니다.](https://www.inflearn.com/questions/23530)
- spring-session-jdbc: [세션 저장소로 DB 이용하기](https://seokr.tistory.com/818)
- multiple datasource: [spring boot jpa Multiple datasource](http://wonwoo.ml/index.php/post/780)
- column encrypt/decrypt : [암복호화 시점과 주체 선정 - JPA AttributeConverter](https://prohannah.tistory.com/82)
- swagger : [Swagger 로 API 문서 자동화하기](https://jojoldu.tistory.com/31) 

### 참고 서적

- 서적1: [자바 ORM 표준 JPA 프로그래밍](http://www.acornpub.co.kr/book/jpa-programmig)
- 서적2: [스프링 부트와 AWS 로 혼자 구현하는 웹 서비스](http://www.yes24.com/Product/Goods/83849117)
