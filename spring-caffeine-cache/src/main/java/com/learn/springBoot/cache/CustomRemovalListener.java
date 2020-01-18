package com.learn.springBoot.cache;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;


/**
 * @author rajes
 * date    Jan 30, 20171:07:42 AM
 *
 */
public class CustomRemovalListener implements RemovalListener {
	
	private static final Logger log = LoggerFactory.getLogger(CustomRemovalListener.class);

	/* (non-Javadoc)
	 * @see com.github.benmanes.caffeine.cache.RemovalListener#onRemoval(java.lang.Object, java.lang.Object, com.github.benmanes.caffeine.cache.RemovalCause)
	 */
	@Override
	public void onRemoval(Object key, Object value, RemovalCause cause) {
  log.info("Removal listener called with key= {}, cause= {} , evicted= {}",key,cause.toString(),cause.wasEvicted());

	}

}
