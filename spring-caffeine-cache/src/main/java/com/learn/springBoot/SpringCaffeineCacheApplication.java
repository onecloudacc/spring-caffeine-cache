package com.learn.springBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.learn.springBoot.service.TopicService;

@SpringBootApplication
@EnableCaching
//@ComponentScan("com.learn.springBoot.cache")
public class SpringCaffeineCacheApplication {

	//@Autowired
	//private static  CacheManager cacheManager;
	
	//@Autowired
	//private TopicService topicService;
	
	private static final Logger log = LoggerFactory.getLogger(SpringCaffeineCacheApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringCaffeineCacheApplication.class, args);
		
		//log.info("Using cache Manager {}", cacheManager.getClass().getSimpleName());
		
	}
}
