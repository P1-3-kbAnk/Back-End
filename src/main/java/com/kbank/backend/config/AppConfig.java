package com.kbank.backend.config;


import com.kbank.backend.exception.CommonException;
import com.kbank.backend.exception.ErrorCode;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/*
    제목 : Spring App 설정 파일
    설명 : 데이터베이스 연결, MyBatis, JPA 연결 설정
    작성자 : 김성헌, 문환희
    일시 : 2024.09.20
*/

@Slf4j // 롬복 로깅 라이브러리
@Configuration //spring 설정 파일 -> xml 사용 안해도 됨
@EnableTransactionManagement // 트랜잭션 관리를 활성. (ex/오류 발생시 작업 취소 롤백)
@EnableJpaRepositories(basePackages = "com.kbank.backend") //JPA리포지토리 찾도록 설정 (설정 안하면 JpaRepository 상속 못받음 중요함!)))
@ComponentScan(basePackages = "com.kbank.backend") // 서비스,리포지,컨트롤러 스캔하여 빈 등록
@MapperScan("com.kbank.backend.mapper")//mybatis 매퍼 스캔
@PropertySources({
        @PropertySource("classpath:application.properties"),
//        @PropertySource("classpath:application-oauth.properties"),
        @PropertySource("classpath:application-jpa.properties")

})
public class AppConfig {

    public AppConfig() {
        System.out.println("Server Started");
    }

    /* Hibernate 정보 */
    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    //Hibernate 설정을 프로퍼티 형태로 반환
    //Hibernate의 SQL 방언, SQL 출력 여부, SQL 포맷팅, DDL 자동 생성 정책 등을 설정
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        return properties;
    }

    // JPA EntityManagerFactory 설정
    // spring 3.1부터 persistence.xml 사용 안하고 @Entity를 이걸로 처리함
    // JPA에서 데이터베이스와 상호작용할 때 사용하는 주요 객체
    // 데이터베이스 테이블과 매핑된 엔티티 클래스들을 관리
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        log.info("Local factory");
//        //데이터 소스 설정
//        em.setDataSource(dataSource);
//        //JPA 엔티티가 위치한 패키지를 스캔하여 관리할 수 있도록 설정
//        em.setPackagesToScan("com.kbank.backend.domain");
//
//        //JPA 구현체 (Hibernate)를 설정합니다.
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        //Hibernate 관련 설정 적용
//        em.setJpaProperties(hibernateProperties());
//
//        return em;
//    }

    // 트랜잭션 관리를 설정, JPA에서 사용할 트랜잭션 매니저를 등록
    // 엔티티 매니저 팩토리와 연결
    //ex/ 여러 데이터 작업이 성공적으로 완료되면 커밋하고, 오류가 발생하면 롤백
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    // FAST API로 리포트 작성 요청을 보내는 빈 생성
    // 타임 아웃 설정으로 무한 대기 방지.
    @Bean
    public RestTemplate restTemplate() {
        HttpClient httpClient = HttpClients.createDefault();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        factory.setConnectionRequestTimeout(200 * 1000); // 커넥션풀에서 사용 가능한 연결을 가져오기 위해 대기하는 최대 시간
        factory.setConnectTimeout(5 * 1000); // 커넥션 최대 시간
        factory.setConnectionRequestTimeout(60 * 1000);

        return new RestTemplate(factory);
    }
}
