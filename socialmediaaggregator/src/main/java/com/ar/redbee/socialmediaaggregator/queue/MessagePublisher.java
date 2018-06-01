package com.ar.redbee.socialmediaaggregator.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicDto;
import com.ar.redbee.socialMediaAggregator_Foundation.utils.JsonHelper;

@Service
public class MessagePublisher implements IMessagePublisher<TopicDto>
{
	//@Autowired
    private RedisTemplate<String,Object> redisTemplate;
	
	@Autowired
    private ChannelTopic topic;
	 
    public MessagePublisher() {
    }
	 
    public MessagePublisher(
      RedisTemplate<String,Object> redisTemplate, ChannelTopic topic) {
      this.redisTemplate = redisTemplate;
      this.topic = topic;
    }

	public void publish(TopicDto message) {
		redisTemplate.convertAndSend(topic.getTopic(), JsonHelper.serialize(message));
	}
}

