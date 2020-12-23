package com.mcit.pm.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mcit"})
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException, URISyntaxException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSourceOracle());
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.mcit.pm.model.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

//    @Bean
//    @Autowired
//    public DataSource dataSourceOracle() throws SQLException {
//        OracleDataSource dataSource = new OracleDataSource();
//        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
//        dataSource.setUser("admin_pm");
//        dataSource.setPassword("admin");
//        return dataSource;
//    }

    
    
     @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }   
    
//    @Bean
//    public DriverManagerDataSource getDataSource() {
//
//        DriverManagerDataSource bds = new DriverManagerDataSource();
//        bds.setDriverClassName("org.postgresql.Driver");
////        bds.setUrl("postgres://mvoqqhqyedhlyd:5bc25d518e62a9029b7731bbf7deb9f532badc8571f9bca0be29e0bb5842d61a@ec2-34-237-166-54.compute-1.amazonaws.com:5432/dd33m8v27c1o86");
////        bds.setUsername("mvoqqhqyedhlyd");
////        bds.setPassword("5bc25d518e62a9029b7731bbf7deb9f532badc8571f9bca0be29e0bb5842d61a");       
//        bds.setUrl("jdbc:postgresql://localhost:5432/admin_pm");
//        bds.setUsername("postgres");
//        bds.setPassword("admin");
//        return bds;
//    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL91Dialect");        
//        hibernateProperties.setProperty(
//                "hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }
}
