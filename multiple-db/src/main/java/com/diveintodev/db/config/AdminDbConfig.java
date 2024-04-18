package com.diveintodev.db.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.diveintodev.db.admin.repository"},
        entityManagerFactoryRef = "adminEntityManagerFactory",
        transactionManagerRef = "adminTransactionManager"
)
public class AdminDbConfig {

    @Bean(name = "adminDataSource")
    @ConfigurationProperties( prefix = "spring.admindb.datasource")
    public DataSource adminDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "adminEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory(EntityManagerFactoryBuilder builder,
                @Qualifier("adminDataSource") DataSource adminDataSource){

        return builder.dataSource(adminDataSource)
                .packages("com.diveintodev.db.admin.entity")
                .build();
    }

    @Bean(name = "adminTransactionManager")
    public PlatformTransactionManager adminTransactionManager(@Qualifier("adminEntityManagerFactory") EntityManagerFactory adminEntityManagerFactory){
        return new JpaTransactionManager(adminEntityManagerFactory);
    }
}
