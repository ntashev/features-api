package com.task.features.persistence.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Persistence related configuration.
 */
@Configuration
@EnableTransactionManagement()
@EnableJpaRepositories("com.task.features.persistence")
public class PersistenceConfiguration {

    @Value("${DB_HOST:}")
    private String dbHost;
    @Value("${DB_PORT:}")
    private String dbPort;
    @Value("${DB_NAME:}")
    private String dbName;
    @Value("${DB_USER_NAME:}")
    private String dbUserName;
    @Value("${DB_PASSWORD:}")
    private String dbPassword;

    /**
     * Creates entity manager factory bean.
     *
     * @return entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.task.features.persistence.entity");

        Properties hibernateProperties = getHibernateProperties();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties);

        return em;
    }

    @Bean
    public Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        return hibernateProperties;
    }

    /**
     * Creates data source for db connection.
     *
     * @return data source bean
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }

    /**
     * Creates transaction manager.
     *
     * @param entityManagerFactory entity manager factory
     * @return transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    /**
     * Creates persistence exception post processor.
     *
     * @return persistence exception post processor
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
