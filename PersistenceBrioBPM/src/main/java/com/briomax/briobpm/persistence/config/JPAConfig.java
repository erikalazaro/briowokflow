
package com.briomax.briobpm.persistence.config;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAutoConfiguration
@EntityScan(value = {"com.briomax.briobpm.persistence.entity", "com.briomax.briobpm.persistence.entity.namedquery"})
@EnableJpaRepositories(value = {"com.briomax.briobpm.persistence.repository"})
@EnableTransactionManagement
public class JPAConfig {
	@Autowired
	private Environment env;
	

	/** Instantiates a new JPA config. */
	protected JPAConfig() {
	}	
	

	/**
	 * Main data source.
	 * @return the data source
	 */
	@Bean(name = "dataSourceBPM")
	@Primary
	public DataSource mainDataSource() {
		return configureDataSource();
	}
	
	private DataSource configureDataSource() {
		String activeDb = env.getProperty("spring.datasource.active.manager");
		   
	        HikariConfig config = new HikariConfig();

            config.setJdbcUrl(env.getProperty("spring.datasources.jdbc-url"));
            config.setUsername(env.getProperty("spring.datasources.username"));
            config.setPassword(env.getProperty("spring.datasources.password"));
            config.setDriverClassName(env.getProperty("spring.datasources.driverClassName"));
            /* System.setProperty("hibernate.default_schema", env.getProperty("spring.datasources.schema"));
            
	       if ("mariadb".equalsIgnoreCase(activeDb)) {
	            config.setJdbcUrl(env.getProperty("spring.datasources.mariadb.jdbcUrl"));
	            config.setUsername(env.getProperty("spring.datasources.mariadb.username"));
	            config.setPassword(env.getProperty("spring.datasources.mariadb.password"));
	            config.setDriverClassName(env.getProperty("spring.datasources.mariadb.driver-class-name"));
	            System.setProperty("hibernate.default_schema", env.getProperty("spring.datasources.mariadb.schema"));
	            
	        } else {
	            config.setJdbcUrl(env.getProperty("spring.datasources.sqlserver.jdbc-url"));
	            config.setUsername(env.getProperty("spring.datasources.sqlserver.username"));
	            config.setPassword(env.getProperty("spring.datasources.sqlserver.password"));
	            config.setDriverClassName(env.getProperty("spring.datasources.driverClassName"));
	            System.setProperty("hibernate.default_schema", env.getProperty("spring.datasources.sqlserver.schema"));
	        }*/
	        DataSource datasource = new HikariDataSource(config);
	        return datasource;
	}

	/**
	 * Tx manager.
	 * @return the platform transaction manager
	 */
	public PlatformTransactionManager txManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(mainDataSource());
		return transactionManager;
	}

}
