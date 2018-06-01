package com.ar.redbee.socialMediaAggregator_Daemon.dto;

import java.util.List;

public class UserDataFeedDto {
	String userName;
	List<UserDataFeedTopic> userTopics;
	List<UserDataFeedTopic> interestTopics;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<UserDataFeedTopic> getUserTopics() {
		return userTopics;
	}
	public void setUserTopics(List<UserDataFeedTopic> userTopics) {
		this.userTopics = userTopics;
	}
	public List<UserDataFeedTopic> getInterestTopics() {
		return interestTopics;
	}
	public void setInterestTopics(List<UserDataFeedTopic> interestTopics) {
		this.interestTopics = interestTopics;
	}
	
	
}
