package com.diveintodev.db.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.diveintodev.db.user.repository"},
        entityManagerFactoryRef = "userEntityManagerFactoryBean",
        transactionManagerRef = "userTransactionManager"
)
public class UserDbConfig {

    @Primary
    @ConfigurationProperties(prefix = "spring.userdb.datasource")
    @Bean(name = "userDataSource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "userEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                   @Qualifier("userDataSource") DataSource userDataSource) {
        return builder.dataSource(userDataSource)
                .packages("com.diveintodev.db.user.entity")
                .build();
    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(@Qualifier("userEntityManagerFactoryBean")
                     EntityManagerFactory userEntityManagerFactoryBean){
            return new JpaTransactionManager(userEntityManagerFactoryBean);
    }

}
