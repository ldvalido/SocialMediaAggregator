package com.ar.redbee.socialmediaaggregator.queue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageReader implements IMessageReader {

	RedisTemplate<String,Object> template;
	
	public MessageReader(
			RedisTemplate<String,Object> redisTemplate) {
		template = redisTemplate;
	}
	@Override
	public String getByKey(String keyName) {
		 return template.opsForValue().get(keyName).toString();
	}

}
