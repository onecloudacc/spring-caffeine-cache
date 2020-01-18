package com.learn.springBoot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learn.springBoot.model.Topic;
import com.learn.springBoot.repository.TopicRepository;

@Service
@CacheConfig(cacheNames={"TOPICS"})
public class TopicService {
	
	private static final Logger log = LoggerFactory.getLogger(TopicService.class);
	
	@Autowired //( when spring creates the topic service instance, it is going to inject the topicrepository here)
	private TopicRepository topicRepository;
	
List<Topic> staticTopics=	new ArrayList<>(Arrays.asList(
			   new Topic("java", "Java8", "A course about java"),
			   new Topic("javaScript", "JavaScript8.0", "A course about javascript")));

public List<Topic> getAllStaticTopics() {
	return staticTopics;
	}


public List<Topic> getAllTopics() {
	
	System.out.println("Getting All topics from the database");
	List<Topic> Alltopics = new ArrayList<>();
	//topicRepository.findAll().forEach(Alltopics::add );//method reference (add method)in java 8 using lamda 
	Iterable<Topic> iterable = topicRepository.findAll();
	for(Topic t : iterable) 
	{
		Alltopics.add(t); //http://stackoverflow.com/questions/6416706/easy-way-to-change-iterable-into-collection
	}
	return Alltopics;
	 //create a new list
	 //findall returns a iterable, we are iterating over the results of iterable and putting each of the entry into a list here.
	}
@Cacheable(unless ="#result == null")  // The @cacheable takes the patameter from the method and looks into teh cache if that exists, if yes it retuns from the cache. If not, the the entre method gets executed and tyhe data is fetched from DB, stored into Cache and then returned. 
public Topic getTopic(String id) { // resultis built in context and it is called spel language. Dont cache it , if it is null.
	log.info("Getting the results for topicId from DB and it is a cache miss: " + id );
	return topicRepository.findOne(id);
	}


// when ever a new topic is added, it should be populated into cache with they cache key as topic.id. The cache wll be created ifresults are NOT null and topic id >0
@CachePut(key="#topic.id" ,unless="#results == null" )
public void addTopic(Topic topic) {
	
	
	log.info("Adding a new topic to the database : " + topic.getId());
   topicRepository.save(topic);  

}	

@CacheEvict( key="#topic.id")
public void updateTopic(String id, Topic topic) {
	log.info("Cache evict for update operation for the topic id {}",id);
	log.info("Updating for topic Id : " + id);
	topicRepository.save(topic);
	
}
// Save method is used for both insert(add) and update. If the row doesnot exist, it will insert, if already exists , it will update.
@CacheEvict
public void deleteTopic(String id) {
	System.out.println(" Deleting the topic Id : " + id );
topicRepository.delete(id);
	
}


public List<Topic> getTopicByName(String nameParam) {
	// TODO Auto-generated method stub
	System.out.println("Getting topics for name : " + nameParam);
	return  topicRepository.findByName(nameParam);
}


}
