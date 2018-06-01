package com.ar.redbee.socialMediaAggregator_Daemon.queue;

import org.springframework.data.redis.core.RedisTemplate;

public class MessageRedisWriter implements IMessageWriter {

	RedisTemplate<String, Object> template;
	public MessageRedisWriter(RedisTemplate<String,Object> template) {
		this.template = template;
	}
	@Override
	public void write(String keyName, String keyValue) {
		// TODO Auto-generated method stub
		template.opsForValue().set(keyName, keyValue);
	}

}
