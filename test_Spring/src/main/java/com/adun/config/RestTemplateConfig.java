package com.adun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author ADun
 * @date 2022/9/21 11:11
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);

        //修改字符集
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter)
                        httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
                break;
            }
        }

        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
