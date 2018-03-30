package com.ldlood;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ChatApplicationTests {
	 Logger log = Logger.getLogger(ChatApplicationTests.class);
    @Test
    public void contextLoads() {
        log.info("info");
        log.error("error");
    }

}
