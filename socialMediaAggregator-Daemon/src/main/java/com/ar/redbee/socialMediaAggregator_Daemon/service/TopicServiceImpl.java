package com.ar.redbee.socialMediaAggregator_Daemon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.redbee.socialMediaAggregator_Daemon.dto.UserDataFeedDto;
import com.ar.redbee.socialMediaAggregator_Daemon.dto.UserDataFeedTopic;
import com.ar.redbee.socialMediaAggregator_Daemon.entity.TopicEntity;
import com.ar.redbee.socialMediaAggregator_Daemon.queue.MessageRedisWriter;
import com.ar.redbee.socialMediaAggregator_Daemon.repository.TopicRepository;
import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicDto;
import com.ar.redbee.socialMediaAggregator_Foundation.utils.JsonHelper;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	MessageRedisWriter redisWriter; 
	
	@Override
	public void process(TopicDto dto) {
		TopicEntity entity = null;
		switch (dto.getTopicOperation()) {
			case CREATE:
				entity = fillEntity(dto);
				topicRepository.save(entity);
				break;
			case DELETE:
				topicRepository.delete(dto.getId());
				break;
			case UPDATE:
				entity = fillEntity(dto);
				entity.setId(dto.getId());
				topicRepository.save(entity);
				break;
			default:
				break;
		}
		processBoard(dto);
		notifyChange(dto);
	}
	
	void notifyChange(TopicDto dto) {
		//TODO: Implement FCM
	}
	
	void processBoard(TopicDto dto ) {
		List<TopicEntity> lstRaw = topicRepository.getPostByUserName(dto.getUserName());
		UserDataFeedDto userFeedData = new UserDataFeedDto();
		userFeedData.setUserName(dto.getUserName());
		
		List<UserDataFeedTopic> lstUserdata = 
				lstRaw.stream().map(el -> new UserDataFeedTopic(el.getPatternFind(),el.isUserTopic())).collect(Collectors.toList());
		
		userFeedData.setInterestTopics(
				lstUserdata.stream().filter(topic -> !topic.isUserTopic()).collect(Collectors.toList()));
		userFeedData.setUserTopics(
				lstUserdata.stream().filter(topic -> topic.isUserTopic()).collect(Collectors.toList() ));
		
		redisWriter.write(dto.getUserName(),JsonHelper.serialize(userFeedData));
	}
	TopicEntity fillEntity(TopicDto dto) {
		TopicEntity entity = new TopicEntity();
		entity.setUserName(dto.getUserName());
		entity.setPatternFind(dto.getPatternFind());
		entity.setUserTopic(parsePattern(entity));
		return entity;
	}
	
	boolean parsePattern(TopicEntity entity) {
		boolean returnValue = false;
		if (entity.getPatternFind().startsWith("@")) {
			//User Interest
			returnValue = true;
		}else {
			//Theme
			returnValue = false;
		}
		return returnValue;
	}
	
	
}
