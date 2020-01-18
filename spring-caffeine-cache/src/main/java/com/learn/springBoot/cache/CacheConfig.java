package com.learn.springBoot.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author rajes
 * date    Jan 29, 20173:14:05 AM
 *
 */
@Configuration
public class CacheConfig {
	
	private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
	@Bean // Bean is a method level annotation, return the bean of type Cachemanager
	public CacheManager cacheManager()
	{
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("TOPICS");
		caffeineCacheManager.setCaffeine(caffeineCacheBuilder());
		return caffeineCacheManager;
		
	}

	/**
	 * @return
	 */
	private Caffeine<Object, Object> caffeineCacheBuilder() {
		
		log.info("In caffeineCacheBuilder  method");
	
		return Caffeine.newBuilder()
				.initialCapacity(100)
		        .expireAfterWrite(10, TimeUnit.SECONDS)
		        .maximumSize(150)
		        .removalListener(new CustomRemovalListener())
		        .recordStats();
		
	}

}
