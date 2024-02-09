package com.staroot.collector.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com/staroot.collector/mapper"}, sqlSessionFactoryRef = "firstDbSqlSessionFactory")
public class FirstDbConfig {
        private final ApplicationContext applicationContext;

        public FirstDbConfig(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        // First DB에 접근 가능한 DataSource Bean 등록
        @Primary
        @Bean(name = "firstDataSource")
        @ConfigurationProperties("spring.datasource.first")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        // First DB 용 sqlSessionFactory Bean 등록
        @Primary
        @Bean(name = "firstDbSqlSessionFactory")
        public SqlSessionFactory sqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {
            final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(dataSource);
            //factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-config.xml"));
            factoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
            return factoryBean.getObject();
        }

        // First DB 용 TransactionManager Bean 등록
    /*
        @Primary
        @Bean(name = "firstDbTransactionManager")
        public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("firstDataSource") DataSource dataSource) {
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
            transactionManager.setDataSource(dataSource);
            return transactionManager;
        }
     */
    }
