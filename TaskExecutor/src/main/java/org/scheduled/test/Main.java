package org.scheduled.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ScheduledTaskService bean = context.getBean(ScheduledTaskService.class);
        System.out.println(":"+FORMAT.format(new Date()));
    }
}
