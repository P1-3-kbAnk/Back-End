package com.kbank.backend.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
    제목 : Servlet 초기 설정
    작성자 : 김성헌
    일시 : 2024.09.20
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebAppInitializer(){
        System.out.println("WebAppInitializer created");
    }

    // 기본 설정 파일 지정
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class, MyBatisConfig.class };
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
}

