package com.learn.springBoot.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.learn.springBoot.model.Topic;
import com.learn.springBoot.service.TopicService;

@RestController
public class TopicController {
	
	private static final Logger log = LoggerFactory.getLogger(TopicController.class);
	
	@Autowired
	private TopicService topicService;
	
private final CacheManager cacheManager;
	
	
    @Autowired
	public TopicController( CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	
@RequestMapping(value="/testTopics")
public String getAllTopicsTest()
{
	return "All Test topics";
}
	


@RequestMapping(value="/staticTopics")
public List<Topic> getAllStaticTopics()
{
	return topicService.getAllStaticTopics();
}

@RequestMapping(value="/topics")
public List<Topic> getAllTopics()
{
	return topicService.getAllTopics();
}

@RequestMapping(value="/topics/{id}")
public Topic getTopic(@PathVariable("id") String id)
{
	log.info(".get...");
	return topicService.getTopic(id);
}

@RequestMapping(value="/topics/name/{nameParam}")
public List<Topic> getTopicByName(@PathVariable("nameParam") String nameParam)
{
	return topicService.getTopicByName(nameParam);
}

@RequestMapping(method=RequestMethod.POST,value="/topics")
public void addTopic( @RequestBody Topic topic) //Request payload will contain json representation of topic instance
{
	log.info("...");
	 topicService.addTopic(topic);
}

@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
public void addTopic( @RequestBody Topic topic,@PathVariable("id") String id) //Request payload will contain json representation of topic instance 
{
	 topicService.updateTopic(id,topic);
}

@RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
public void addTopic( @PathVariable("id") String id) //Request payload will contain json representation of topic instance 
{
	 topicService.deleteTopic(id);
}

//Prints the cache stats 
@RequestMapping(method=RequestMethod.GET,value="/cacheStats")
public void getCoffeeCacheStats() {
    org.springframework.cache.Cache cache = cacheManager.getCache("TOPICS");
    Cache nativeCoffeeCache = (Cache) cache.getNativeCache();
     System.out.println(nativeCoffeeCache.stats());
   //return nativeCoffeeCache.stats();
}


}
