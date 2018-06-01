package com.ar.redbee.socialmediaaggregator.queue;

public interface IMessagePublisher<T> {
	void publish(T message);
}
