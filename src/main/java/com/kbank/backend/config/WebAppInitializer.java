package com.kbank.backend.config;

import com.kbank.backend.security.config.ECryptConfig;
import com.kbank.backend.security.config.OAuth2Config;
import com.kbank.backend.security.config.SecurityConfig;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebAppInitializer(){
        System.out.println("WebAppInitializer created");
    }

    // 기본 설정 파일 지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                AppConfig.class,
                MyBatisConfig.class,
                JpaConfig.class,
                SecurityConfig.class,
                OAuth2Config.class,
                ECryptConfig.class
        }; //SecurityConfig.class
    }

    // Servlet 설정 파일 지정
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @NotNull
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        return new Filter[] { characterEncodingFilter, securityFilterChain };
    }
}

