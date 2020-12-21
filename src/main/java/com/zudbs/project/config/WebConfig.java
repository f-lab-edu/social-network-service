package com.zudbs.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class WebConfig {

    /*
    Spring은 Multipart 형식을 지원하는 기능을 제공한다.(CommonsMultipartResolver)
    이 기능을 사욯하기 위해서는 ID가 "multipartResolver"인 Bean을 등록해야 한다.
    */
    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver() {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();

        return multipartResolver;
    }

}
