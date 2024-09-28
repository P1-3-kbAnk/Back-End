package com.kbank.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableTransactionManagement
@MapperScan("com.kbank.backend.mapper") // 매퍼 스캔 경로
public class MyBatisConfig {
    /* JDBC 정보 */
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

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

    // SqlSessionFactory 설정
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml")); // MyBatis 설정 파일 경로
        return sessionFactory.getObject();
    }

    // SqlSessionTemplate 설정
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // TransactionManager 설정
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}