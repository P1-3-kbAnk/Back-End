package com.kbank.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@PropertySource("classpath:application.properties")//properties 파일에서 설정 불러옴
@ComponentScan(basePackages = "com.kbank.backend") // 서비스,리포지,컨트롤러 스캔하여 빈 등록
@MapperScan("com.kbank.backend.domain.address")//mybatis 매퍼 스캔
public class AppConfig {

    public AppConfig() {
        System.out.println("Server Started");
    }
    /* JDBC 정보 */
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /* Hibernate 정보 */
    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    /* HikariCP 설정 */
    @Value("${hikari.maximumPoolSize:10}")
    private int maximumPoolSize;


    // HikariCP 설정을 사용하여 데이터베이스에 연결하는 데이터 소스를 생성
    //이 데이터 source는 MyBatis와 JPA에서 모두 사용
    @Bean
    public DataSource dataSource() {
        // HikariConfig 설정
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setPoolName("HikariCP-Pool");

        // 추가 커넥션 풀 설정
        hikariConfig.setMaximumPoolSize(10); // 최대 커넥션 수
        hikariConfig.setMinimumIdle(5); // 최소 유휴 커넥션 수
        hikariConfig.setConnectionTimeout(30000); // 커넥션 타임아웃 (밀리초)
        hikariConfig.setIdleTimeout(600000); // 유휴 커넥션의 최대 유지 시간
        hikariConfig.setMaxLifetime(1800000); // 커넥션의 최대 수명 시간 (밀리초)

        // HikariDataSource 객체 생성 및 반환
        return new HikariDataSource(hikariConfig);
    }

    // MyBatis SqlSessionFactory 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sessionFactory.getObject();
    }

    // MyBatis SqlSessionTemplate 설정
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

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
    //PA에서 데이터베이스와 상호작용할 때 사용하는 주요 객체
    // 데이터베이스 테이블과 매핑된 엔티티 클래스들을 관리
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        //데이터 소스 설정
        em.setDataSource(dataSource);
        //JPA 엔티티가 위치한 패키지를 스캔하여 관리할 수 있도록 설정
        em.setPackagesToScan("com.kbank.backend.domain");

        //JPA 구현체 (Hibernate)를 설정합니다.
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        //Hibernate 관련 설정 적용
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    // 트랜잭션 관리를 설정, JPA에서 사용할 트랜잭션 매니저를 등록
    // 엔티티 매니저 팩토리와 연결
    //ex/ 여러 데이터 작업이 성공적으로 완료되면 커밋하고, 오류가 발생하면 롤백
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}
