package com.adun.rest;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author ADun
 * @date 2022/9/21 11:18
 */
@SpringBootTest
public class RestTemplateTests {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Test
    public void testGet(){
        String url="";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(url, HttpMethod.GET,httpEntity, String.class);
    }
}
