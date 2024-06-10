package com.ohgiraffers.jwtsecurity.auth.config;

import com.ohgiraffers.jwtsecurity.auth.filter.HeaderFilter;
import com.ohgiraffers.jwtsecurity.auth.interceptor.JwtTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* Web configuration을 위한 클래스
* */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /* 정적 자원 배열*/
    /* 요청 왔을때 선언해줌 */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/", "classpath:/public/", "classpath:/", "classpath:/resources/",
            "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"
    };


    /* 선언한 정적 자원을 허용 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Bean
    public FilterRegistrationBean<HeaderFilter> getFilterRegistrationBean() {

        FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<HeaderFilter>(createHeaderFilter());

        registrationBean.setOrder(Integer.MIN_VALUE);

        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public HeaderFilter createHeaderFilter() {

        return new HeaderFilter();
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }

}
