package com.kbank.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import javax.servlet.Filter;
import java.util.List;

/*
    제목 : 웹 초기 설정
    작성자 : 김성헌
    일시 : 2024.09.20
 */


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.kbank.backend")
public class WebConfig implements WebMvcConfigurer {

    public WebConfig(){
        System.out.println("WebConfig created");
    }

    // JSP 뷰어 -> 관리자 페이지에 사용?
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // resource 파일 경로 지정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}

