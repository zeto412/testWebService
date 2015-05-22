package com.vladimir.testService.config;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.vladimir.testService")
@PropertySource("classpath:app.properties")
public class TestDataBaseConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "db.hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
//    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";


//    private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:3306/testdb";
//    private static final String PROPERTY_NAME_DATABASE_USERNAME = "root";
//    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "99987565";

//    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
//    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
//    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.vladimir.testService.entity";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "create-drop";

    @Resource
    private Environment _environment;

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(_environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(_environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(_environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(_environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

//        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
//        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
//        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
//        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(_environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));


//        entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);

        entityManagerFactoryBean.setJpaProperties(getHibernateProp());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProp() {

        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, _environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, _environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
//        properties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, _environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
//

//        properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
//        properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
        properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO);
        return properties;
    }

}
