package com.ar.redbee.socialmediaaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.ar.redbee.socialmediaaggregator.queue.RedisConfig;

@SpringBootApplication
@EnableCaching
public class App 
{
    public static void main( String[] args )
    {
    	Object[] sources = {App.class,RedisConfig.class};

		SpringApplication.run(sources, args);
    }
}
