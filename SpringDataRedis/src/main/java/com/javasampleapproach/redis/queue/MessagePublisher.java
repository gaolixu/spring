package com.javasampleapproach.redis.queue;
public interface MessagePublisher {

    void publish(final String message);
}