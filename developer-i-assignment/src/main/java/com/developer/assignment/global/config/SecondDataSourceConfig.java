package com.developer.assignment.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "secondEntityManagerFactory",
    transactionManagerRef = "secondTransactionManager",
    basePackages = {"com.developer.assignment.repository.second"}
)
public class SecondDataSourceConfig {

    private final Environment env;

    @Bean(name = "secondDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.second-datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.second-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.second-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.second-datasource.password"));

        return dataSource;
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("secondDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.developer.assignment.domain")
                .persistenceUnit("secondDataSource")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }
}
