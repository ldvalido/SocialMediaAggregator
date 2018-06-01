package com.ar.redbee.socialMediaAggregator_Daemon.queue;

public interface IMessageWriter {
	void write(String keyName, String value);
}
