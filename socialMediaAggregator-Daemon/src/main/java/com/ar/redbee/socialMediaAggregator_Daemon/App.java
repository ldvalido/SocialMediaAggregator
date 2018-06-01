package com.ar.redbee.socialMediaAggregator_Daemon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ar.redbee.socialMediaAggregator_Daemon.queue.RedisConfig;

@EnableCaching
@SpringBootApplication
@EnableJpaRepositories
public class App  implements CommandLineRunner
{ 
	public static void main( String[] args )
	{
		Object[] sources = {App.class,RedisConfig.class};

		SpringApplication.run(sources, args);		
	}
	
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	}
}
