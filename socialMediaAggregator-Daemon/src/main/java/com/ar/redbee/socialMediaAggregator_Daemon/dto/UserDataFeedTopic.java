package com.ar.redbee.socialMediaAggregator_Daemon.dto;

public class UserDataFeedTopic {
	private String patternFind;
	private boolean userTopic;

	
	public boolean isUserTopic() {
		return userTopic;
	}

	public void setUserTopic(boolean userTopic) {
		this.userTopic = userTopic;
	}

	public String getPatternFind() {
		return patternFind;
	}

	public void setPatternFind(String patternFind) {
		this.patternFind = patternFind;
	}

	public UserDataFeedTopic(String patternFind, boolean userTopic) {
		super();
		this.patternFind = patternFind;
		this.userTopic = userTopic;
	}
	
	
	

}
