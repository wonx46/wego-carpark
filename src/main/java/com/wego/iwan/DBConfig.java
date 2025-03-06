package com.wego.iwan;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		  entityManagerFactoryRef = "wegoEntityManagerFactory",
		  transactionManagerRef = "wegoTransactionManager",
		  basePackages = { "com.wego.iwan.repository" }
		)
public class DBConfig {
	
	  @Primary
	  @Bean(name = "wegodataSource")
	  @ConfigurationProperties(prefix = "spring.datasource.wego")
	  public DataSource dataSource() {
	    return DataSourceBuilder.create().build();
	  }
	  
	  @Primary
	  @Bean(name = "wegoEntityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  entityManagerFactory(
	    EntityManagerFactoryBuilder builder,
	    @Qualifier("wegodataSource") DataSource dataSource
	  ) {
	    return builder
	      .dataSource(dataSource)
	      .packages("com.wego.iwan.model")
	      .persistenceUnit("wego")
	      .build();
	  }
	    
	  @Primary
	  @Bean(name = "wegoTransactionManager")
	  public PlatformTransactionManager transactionManager(
	    @Qualifier("wegoEntityManagerFactory") EntityManagerFactory 
	    wegoentityManagerFactory
	  ) {
	    return new JpaTransactionManager(wegoentityManagerFactory);
	  }
}
