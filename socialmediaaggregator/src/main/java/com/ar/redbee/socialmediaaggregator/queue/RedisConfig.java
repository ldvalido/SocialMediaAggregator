package com.ar.redbee.socialmediaaggregator.queue;

import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ar.redbee.socialmediaaggregator.queue.MessagePublisher;

@Configuration
@ComponentScan
@EnableRedisRepositories
public class RedisConfig {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() throws Exception {
        //URI redisUri =  new URL(System.getenv("REDISCLOUD_URL"));
        URL redisUri =  new URL("http://localhost:6379");
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(redisUri.getHost());
        redisConnectionFactory.setPort(redisUri.getPort());
        //redisConnectionFactory.setPassword(redisUri.getUserInfo().split(":",2)[1]);
        redisConnectionFactory.setUsePool(true);
        return redisConnectionFactory;
    }

    
    @Bean
    @Primary
    public MessageReader redisReader() throws Exception {
    	final MessageReader redisReader = new MessageReader(redisTemplate());
    	return redisReader;
    }
	
	@Bean
	public RedisTemplate<Object,Object> redisGenericTemplate() throws Exception {
        final RedisTemplate<Object, Object> template = new org.springframework.data.redis.core.RedisTemplate<Object, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }
	
    @Bean
    public RedisTemplate<String,Object> redisTemplate() throws Exception {
        final RedisTemplate<String, Object> template = new org.springframework.data.redis.core.RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        return template;
    }
        
    @Bean
    @Primary
    MessagePublisher redisPublisher() throws Exception {
        return new MessagePublisher(redisTemplate(), topic());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() throws Exception {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        //container.addMessageListener(messageListener(), topic());
        return container;
    }
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("socialMediaAgreggator:pubsub:queue");
    }
}
