package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@Configuration
public class DevConfiguration {

	
	private static final Logger logger = LoggerFactory.getLogger(DevConfiguration.class);

	
	/**
	 * Bean for getting the handle of SessionFactory for
	 * Manually Creating Hibernate Session if needed.
	 * @return
	 */
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		logger.debug("Creating hibernate Session factoy");

		HibernateJpaSessionFactoryBean hibernateJpaSessionFactoryBean = null;
		try{
			hibernateJpaSessionFactoryBean = new HibernateJpaSessionFactoryBean();
		}catch(Exception e){
			logger.debug("Exception occure while create HibernateJpaSessionFactoryBean : "+e);
		}
		logger.debug("HibernateJpaSessionFactoryBean created succesfully.");
		return hibernateJpaSessionFactoryBean;
	}
}
