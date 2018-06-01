package com.ar.redbee.socialMediaAggregator_Daemon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TopicEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String userName;
	private String patternFind;
	private boolean isUserTopic;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public boolean isUserTopic() {
		return isUserTopic;
	}
	public void setUserTopic(boolean isUserTopic) {
		this.isUserTopic = isUserTopic;
	}
	public TopicEntity() {}
	
	
}
