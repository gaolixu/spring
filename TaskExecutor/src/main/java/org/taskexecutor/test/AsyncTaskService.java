package org.taskexecutor.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by sang on 16-12-14.
 */
@Service
public class AsyncTaskService {
    @Async
    public void executeAsyncTask(int i) {
        System.out.println("test1" + i+" thread.currentThread().getName():"+Thread.currentThread().getName());
    }

    @Async
    public void executeAsyncTask2(int i) {
        System.out.println("test2" + i+" thread.currentThread().getName():"+Thread.currentThread().getName());
    }
}
