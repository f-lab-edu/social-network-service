package com.zudbs.project.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
/*
 클래스가 하나 이상의 @Bean메소드를 선언 하고 런타임시 해당 Bean에 대한 정의
  및 서비스 요청을 생성하기 위해 Spring 컨테이너에서 처리 될 수 있음을 나타내는 어노테이션*/
public class DatabaseConfig {

    /* SqlSession(SQL 실행 및 트랜잭션 제어)을 생성하는 구성 요소 */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /*	SQL 실행이나 트랜잭션 관리, SqlSession 인터페이스를 구현, Thread-safe*/
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

