package com.zjh.mysys.configuration;

import com.zjh.mysys.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/personManagement/**");  //对来自/personManagement/** 这个链接来的请求进行拦截
    }
}