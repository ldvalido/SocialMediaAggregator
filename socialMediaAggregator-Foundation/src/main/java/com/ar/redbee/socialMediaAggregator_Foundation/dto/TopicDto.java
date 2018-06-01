package com.ar.redbee.socialMediaAggregator_Foundation.dto;

public class TopicDto {
	private String userName;
	private String patternFind;
	private Integer id;
	private TopicOperation topicOperation;
	
	public TopicOperation getTopicOperation() {
		return topicOperation;
	}
	public void setTopicOperation(TopicOperation topicOperation) {
		this.topicOperation = topicOperation;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPatternFind() {
		return patternFind;
	}
	public void setPatternFind(String paternFind) {
		this.patternFind = paternFind;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
