package com.ar.redbee.socialMediaAggregator_Daemon.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.ar.redbee.socialMediaAggregator_Daemon.service.TopicService;
import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicDto;
import com.ar.redbee.socialMediaAggregator_Foundation.utils.JsonHelper;

@Service
public class MessageConsumer implements MessageListener {
	
	TopicService topicService;
	@Autowired
	public MessageConsumer(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String rawMessage = new String(message.getBody());
		TopicDto dto = JsonHelper.desearilize(rawMessage, TopicDto.class);		
		topicService.process(dto);
	}
}
