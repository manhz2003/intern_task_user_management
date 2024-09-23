package com.fds.nsw.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.fds.nsw.liferay.repository",
        entityManagerFactoryRef = "liferayEntityManagerFactory",
        transactionManagerRef = "liferayTransactionManager"
)
public class LiferayDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.liferay")
    public DataSource liferayDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean liferayEntityManagerFactory(
            @Qualifier("liferayDataSource") DataSource liferayDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(liferayDataSource);
        em.setPackagesToScan("com.fds.nsw.liferay.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public JpaTransactionManager liferayTransactionManager(
            @Qualifier("liferayEntityManagerFactory") EntityManagerFactory liferayEntityManagerFactory) {
        return new JpaTransactionManager(liferayEntityManagerFactory);
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return properties;
    }
}