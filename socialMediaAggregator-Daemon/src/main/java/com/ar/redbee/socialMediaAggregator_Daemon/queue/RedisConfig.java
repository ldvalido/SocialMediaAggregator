package com.ar.redbee.socialMediaAggregator_Daemon.queue;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ar.redbee.socialMediaAggregator_Daemon.service.TopicService;

@Configuration
@ComponentScan
@EnableRedisRepositories
public class RedisConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TopicService topicService;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() throws Exception {
		String rawUrl = env.getProperty("redis.url");
        URL redisUri =  new URL(rawUrl);
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(redisUri.getHost());
        redisConnectionFactory.setPort(redisUri.getPort());
        redisConnectionFactory.setUsePool(true);
        return redisConnectionFactory;
    }

	
	@Bean
	public RedisTemplate<Object,Object> redisGenericTemplate() throws Exception {
        final RedisTemplate<Object, Object> template = new org.springframework.data.redis.core.RedisTemplate<Object, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }
    
    @Bean
    @Primary
    public MessageRedisWriter redisReader() throws Exception {
    	final MessageRedisWriter redisReader = new MessageRedisWriter(redisTemplate());
    	return redisReader;
    }
	
    @Bean
    public RedisTemplate<String,Object> redisTemplate() throws Exception {
        final RedisTemplate<String, Object> template = new org.springframework.data.redis.core.RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageConsumer(topicService));
    }

    @Bean
    RedisMessageListenerContainer redisContainer() throws Exception {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("socialMediaAgreggator:pubsub:queue");
    }
}
