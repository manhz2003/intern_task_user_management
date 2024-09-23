package com.fds.nsw.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.fds.nsw.nghiepvu.repository",
        entityManagerFactoryRef = "nghiepvuEntityManagerFactory",
        transactionManagerRef = "nghiepvuTransactionManager"
)
public class NghiepvuDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.nghiepvu")
    public DataSource nghiepvuDataSource() {
        return DataSourceBuilder.create().build();
    }
    

    @Bean
    public LocalContainerEntityManagerFactoryBean nghiepvuEntityManagerFactory(
            @Qualifier("nghiepvuDataSource") DataSource nghiepvuDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(nghiepvuDataSource);
        em.setPackagesToScan("com.fds.nsw.nghiepvu.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public JpaTransactionManager nghiepvuTransactionManager(
            @Qualifier("nghiepvuEntityManagerFactory") EntityManagerFactory nghiepvuEntityManagerFactory) {
        return new JpaTransactionManager(nghiepvuEntityManagerFactory);
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return properties;
    }
    
	@Bean(name = "jdbcTemplateNghiepVu")
	public JdbcTemplate jdbcTemplate2(@Qualifier("nghiepvuDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
