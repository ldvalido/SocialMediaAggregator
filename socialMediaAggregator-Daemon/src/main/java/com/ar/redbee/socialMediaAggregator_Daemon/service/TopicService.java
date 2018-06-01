package com.ar.redbee.socialMediaAggregator_Daemon.service;

import com.ar.redbee.socialMediaAggregator_Foundation.dto.TopicDto;

public interface TopicService {
	void process(TopicDto dto);
}
