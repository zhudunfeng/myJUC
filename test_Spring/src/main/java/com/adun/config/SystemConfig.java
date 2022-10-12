package com.adun.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration//让springboot扫描此处，配置文件
public class SystemConfig {
    @Bean//相当于之前的xml配置文件注册bean<bean id=''>
    public FilterRegistrationBean characterEncodingFilterRegistrationBean(){
        //设置字符编码格式，强制转换，并设置成utf-8
        CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("utf-8");
        //过滤器注册器注册字符过滤器，并配置使用的范围
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(characterEncodingFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return  filterRegistrationBean;
    }
}
