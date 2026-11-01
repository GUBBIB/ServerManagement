package com.github.gubbib.backend.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MybatisConfig {

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(
            SqlSessionFactory sqlSessionFactory
    ) {
        return new SqlSessionTemplate(
                sqlSessionFactory,
                ExecutorType.SIMPLE
        );
    }

    @Bean("batchSqlSessionTemplate")
    public  SqlSessionTemplate batchSqlSessionTemplate(
            SqlSessionFactory sqlSessionFactory
    ) {
        return new SqlSessionTemplate(
                sqlSessionFactory,
                ExecutorType.BATCH
        );
    }
}
