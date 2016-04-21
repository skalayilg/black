package com.sancho.journal.web;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:config.properties")
@PropertySource(value = "classpath:${ENV}.config.properties", ignoreResourceNotFound = true)
@PropertySource("classpath:db.properties")
@PropertySource(value = "classpath:${ENV}.db.properties", ignoreResourceNotFound = true)
@EnableTransactionManagement
@EnableJpaRepositories("com.sancho.journal.web.dao")

public class JJDatabaseConfig {
    @Autowired
    Environment env;
    
    @Bean(name = "dataSource")
    @Profile("notused")
    public DataSource getDatasourceEmbedded() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }
    
    @Bean(name = "dataSource")
    @Profile("embedded")
    public DataSource getDatasourceSimpleEmbedded() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }
    
    @Bean
    @Profile("dev")
    public DataSource getDatasourceSimple() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("jj.db.driver"));
        ds.setUrl(env.getProperty("jj.db.url"));
        ds.setUsername(env.getProperty("jj.db.username"));
        ds.setPassword(env.getProperty("jj.db.password"));
        return ds;
    }
    
    @Profile("live")
    public DataSource getDatasourceJndi() {
        JndiDataSourceLookup jndidslookup = new JndiDataSourceLookup();
        return jndidslookup.getDataSource(env.getProperty("jj.ds.jndiname"));
        
    }
    
    @Bean
    public JpaVendorAdapter jpaAdapter() {
        return new HibernateJpaVendorAdapter();
    }
    
    /* Method name should be entityManagerFactroy */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds, JpaVendorAdapter jva,
            Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setJpaVendorAdapter(jva);
        emf.setPackagesToScan("com.sancho.journal.web.model");
        emf.setJpaProperties(jpaProperties);
        return emf;
    }
    
    @Bean(name = "jpaProperties")
    @Profile("dev,prod")
    public Properties jpaPropertiesMysql() {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return props;
    }
    
    @Bean(name = "jpaProperties")
    @Profile("embedded")
    public Properties jpaPropertiesH2() {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return props;
    }
    
    /* method name should be transactionManager */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        return tm;
        
    }
}
