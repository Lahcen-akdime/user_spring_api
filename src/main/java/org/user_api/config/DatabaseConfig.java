package org.user_api.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.user_api.Repository")
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl ;

    @Value("${spring.datasource.username}")
    private String dbUsername ;

    @Value("${spring.datasource.password}")
    private String dbPassword ;

    @Value("${spring.datasource.driver-class-name}")
    private String driver ;

    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource() ;
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(driver);
        return dataSource ;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean Factorybean = new LocalContainerEntityManagerFactoryBean() ;
        Factorybean.setDataSource(dataSource);
        Factorybean.setPackagesToScan("org.user_api.Model");
        Factorybean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties() ;
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        Factorybean.setJpaProperties(properties);
        return Factorybean ;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory) ;
    }
}
