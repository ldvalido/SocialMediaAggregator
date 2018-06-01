package com.ar.redbee.socialmediaaggregator.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicDto;
import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicOperation;
import com.ar.redbee.socialMediaAggregator_Foundation.utils.JsonHelper;
import com.ar.redbee.socialmediaaggregator.queue.IMessagePublisher;
import com.ar.redbee.socialmediaaggregator.queue.IMessageReader;


@RestController
@EnableAutoConfiguration
public class TopicController extends BaseController {
	
	@Autowired
	IMessageReader reader;
	
	@Autowired
	IMessagePublisher<TopicDto> publisher;
	
    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    void createTopic(@RequestBody TopicDto topic) {
    	this.getLogger().info("Receiving topic: " + JsonHelper.serialize(topic));
    	topic.setTopicOperation(TopicOperation.CREATE);
    	publisher.publish(topic);
    }
    
    @RequestMapping(value = "/topic", method = RequestMethod.PUT)
    void putTopic(@RequestBody TopicDto topic) {
    	this.getLogger().info("Updating topic: " + JsonHelper.serialize(topic));
    	topic.setTopicOperation(TopicOperation.UPDATE);
    	publisher.publish(topic);
    }
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
    void deleteTopic(@PathVariable("id") Integer id) {
    	this.getLogger().info("Deleting topic: " + id);
    	TopicDto dto = new TopicDto();
    	dto.setId(id);
    	dto.setTopicOperation(TopicOperation.DELETE);
    	publisher.publish(dto);
    }
    
    @RequestMapping(value = "/boards/{userName:[A-z]*}", method = RequestMethod.GET)
    String getBoardByUserName(@PathVariable("userName") String userName) {
    	this.getLogger().info("Request board for: " + userName);
    	return reader.getByKey(userName);
    }
    
    @RequestMapping(value="/echo",method=RequestMethod.POST)
    String echo(@RequestBody String value) {
    	return value;
    }
}
