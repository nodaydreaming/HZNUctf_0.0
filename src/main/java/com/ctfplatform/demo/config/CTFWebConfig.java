package com.ctfplatform.demo.config;

import com.ctfplatform.demo.Filter.AdminSessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CTFWebConfig implements WebMvcConfigurer {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new AdminSessionFilter());
        frBean.addUrlPatterns("/*");
//        System.out.println("filter");
        return frBean;
    }
}
