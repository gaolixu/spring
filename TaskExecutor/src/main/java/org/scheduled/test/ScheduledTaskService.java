package org.scheduled.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("reportCurrentTime:" + FORMAT.format(new Date()));
    }

    @Scheduled(fixedDelay = 10000)
    public void delayExecuteTask() {
        System.out.println("delayExecuteTask 10 seconds");
    }

   
    @Scheduled(cron = "0 51 20 * * ?")
    public void fixTimeExecution() {
        System.out.println("fixTimeExecution:"+FORMAT.format(new Date())+"");
    }
}
