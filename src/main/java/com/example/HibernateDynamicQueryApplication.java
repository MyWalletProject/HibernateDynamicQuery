package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAsync
public class HibernateDynamicQueryApplication {

	
	private static final Logger logger = LoggerFactory.getLogger(HibernateDynamicQueryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HibernateDynamicQueryApplication.class, args);
	}
	
	/**
	 * This bean is used to create Executor bean
	 * Which helps in running heavy operation in background
	 * @return pool of threads managed by spring boot
	 */
//	@Bean
//	public Executor asyncExecutor() {
//		logger.info("Creating Async Executor Service");
//		ThreadPoolTaskExecutor executorpool = new ThreadPoolTaskExecutor();
//		executorpool.setCorePoolSize(10);
//		executorpool.setMaxPoolSize(50);
//		executorpool.setQueueCapacity(100);
//		executorpool.setThreadNamePrefix("kyc_workers");
//		executorpool.initialize();
//		return executorpool;
//	}
	
}
