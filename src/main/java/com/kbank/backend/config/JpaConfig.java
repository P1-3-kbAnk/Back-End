//
package com.kbank.backend.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import jakarta.persistence.EntityManagerFactory;
//import java.util.Properties;
//
//@Configuration
//@Primary
//@EnableJpaRepositories(basePackages = "com.kbank.backend.repository") // 리포지토리 경로 설정
//@EnableTransactionManagement
//public class JpaConfig {
//
//    // Database Configuration
//    @Value("${jdbc.driverClassName}")
//    private String driverClassName;
//
//    @Value("${jdbc.url}")
//    private String url;
//
//    @Value("${jdbc.username}")
//    private String username;
//
//    @Value("${jdbc.password}")
//    private String password;
//
//    // HikariCP Settings
//    @Value("${hikari.maximumPoolSize:10}")
//    private int maximumPoolSize;
//
//    @Value("${hikari.idleTimeout:300}")
//    private long idleTimeout;
//
//    @Value("${hikari.maxLifetime:300}")
//    private long maxLifetime;
//
//    // JPA and Hibernate Settings
//    @Value("${hibernate.dialect}")
//    private String hibernateDialect;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String hbm2ddlAuto;
//
//    @Value("${hibernate.show_sql}")
//    private String showSql;
//
//    @Value("${hibernate.format_sql}")
//    private String formatSql;
//
//    @Value("${hibernate.order_inserts}")
//    private String orderInserts;
//
//    @Value("${hibernate.order_updates}")
//    private String orderUpdates;
//
//    @Value("${hibernate.jdbc.batch_size}")
//    private String batchSize;
//
//    @Value("${hibernate.default_batch_fetch_size}")
//    private String defaultBatchFetchSize;
//
//    @Value("${hibernate.current_session_context_class}")
//    private String sessionContextClass;
//
//    @Value("${hibernate.hbm2ddl.import_files_sql_extractor}")
//    private String importFilesSqlExtractor;
//
//    // Adding the new setting for open-in-view configuration
//    @Value("${jpa.open-in-view}")
//    private Boolean openInView;
//
//    // Adding the new setting for JPA database configuration
//    @Value("${jpa.database}")
//    private String jpaDatabase;
//
//    // Adding the new setting for JPA generate DDL configuration
//    @Value("${jpa.generate-ddl}")
//    private Boolean generateDdl;
//
//    @Value("${jpa.hibernate.ddl-auto}")
//    private String ddlUpdate;
//
//    @Value("${jpa.show-sql}")
//    private Boolean showDdl;
//
//    // 데이터소스 설정 - HikariCP 사용
//    @Primary
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(driverClassName);
//        hikariConfig.setJdbcUrl(url);
//        hikariConfig.setUsername(username);
//        hikariConfig.setPassword(password);
//        hikariConfig.setMaximumPoolSize(maximumPoolSize);
//        hikariConfig.setIdleTimeout(idleTimeout);
//        hikariConfig.setMaxLifetime(maxLifetime);
//        return new HikariDataSource(hikariConfig);
//    }
//
//    // JPA EntityManagerFactory 설정
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true); // DDL 자동 생성
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.kbank.backend.domain"); // 도메인 패키지 설정
//        factory.setDataSource(dataSource());
//        factory.setJpaProperties(jpaProperties()); // 추가 Hibernate 설정
//        return factory;
//    }
//
//    // 트랜잭션 매니저 설정
//    @Primary
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
//
//    // Hibernate 설정을 위한 추가 프로퍼티
//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", hibernateDialect);
//        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.format_sql", formatSql);
//        properties.put("hibernate.order_inserts", orderInserts);
//        properties.put("hibernate.order_updates", orderUpdates);
//        properties.put("hibernate.jdbc.batch_size", batchSize);
//        properties.put("hibernate.default_batch_fetch_size", defaultBatchFetchSize);
//        properties.put("hibernate.current_session_context_class", sessionContextClass);
//        properties.put("hibernate.hbm2ddl.import_files_sql_extractor", importFilesSqlExtractor);
//
//        // Set additional JPA settings
//        properties.put("jpa.open-in-view", openInView.toString());  // Disable open-in-view
//        properties.put("jpa.database", jpaDatabase);  // Set database type (e.g., mysql)
//        properties.put("jpa.generate-ddl", generateDdl.toString());  // Enable/disable DDL generation
//        properties.put("pa.hibernate.ddl-auto", ddlUpdate);  // Enable/disable DDL generation
//        properties.put("jpa.show-sql", showDdl.toString());  // Enable/disable DDL generation
//        return properties;
//    }
//}

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class JpaConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    // HikariCP 설정을 통한 데이터소스 빈 생성
    @Primary
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(300000);
        hikariConfig.setMaxLifetime(1800000);
        return new HikariDataSource(hikariConfig);
    }

    // EntityManagerFactory 설정
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true); // DDL 자동 생성

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.kbank.backend.domain"); // 엔티티 패키지 스캔
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties()); // 추가 Hibernate 설정

        return factory;
    }

    // 트랜잭션 매니저 설정
    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    // JPA 설정을 위한 추가 프로퍼티
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // MySQL 다이얼렉트 사용
        properties.put("hibernate.hbm2ddl.auto", "update"); // 스키마 업데이트
        properties.put("hibernate.show_sql", "true"); // SQL 쿼리 표시
        properties.put("hibernate.format_sql", "true"); // SQL 포맷팅
        properties.put("hibernate.order_inserts", "true");
        properties.put("hibernate.order_updates", "true");
        properties.put("hibernate.jdbc.batch_size", "20");
        properties.put("hibernate.default_batch_fetch_size", "100");
        return properties;
    }
}