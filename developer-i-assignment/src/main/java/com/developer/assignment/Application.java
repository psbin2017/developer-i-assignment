package com.developer.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 애플리케이션 클래스는 항상 프로젝트 상단에 위치해야 한다.
 * `@EnableJpaAuditing` : {@link com.developer.assignment.global.domain.BaseTimeEntity}
 */
// @EnableJpaAuditing
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
public class Application {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
