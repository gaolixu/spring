package com.javasampleapproach.redis.config;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;


@Configuration
//@EnableConfigurationProperties(ChatProperties.class)
//@Profile("test")
public class EmbeddedRedisServer {

    @Value("${spring.redis.port:6379}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException, URISyntaxException {
        //redisPort = org.springframework.util.SocketUtils.findAvailableTcpPort(6380);
        //RedisExecProvider customRedisExec = RedisExecProvider.defaultProvider().override
        //        (OsArchitecture.detect().os(), OsArchitecture.detect().arch(), "redis-server-2.8.19.exe");

        redisServer = RedisServer.builder()
                .port(redisPort)
                //.redisExecProvider(customRedisExec) //com.github.kstyrc (not com.orange.redis-embedded)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() throws InterruptedException {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
