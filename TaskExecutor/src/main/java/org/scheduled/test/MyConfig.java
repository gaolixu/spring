package org.scheduled.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@ComponentScan("org.scheduled.test")
@EnableScheduling
public class MyConfig {
}
