package com.batch;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.MDC;





public class MdcThreadPoolExecutor_old extends ThreadPoolExecutor {

    final private boolean useFixedContext;
    final private Map<String, String> fixedContext;

    /**
     * Pool where task threads take MDC from the submitting thread.
     */
    public static MdcThreadPoolExecutor_old newWithInheritedMdc(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                                            TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor_old(null, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * Pool where task threads take fixed MDC from the thread that creates the pool.
     */
    @SuppressWarnings("unchecked")
    public static MdcThreadPoolExecutor_old newWithCurrentMdc(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                                          TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor_old(MDC.getCopyOfContextMap(), corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue);
    }

    /**
     * Pool where task threads always have a specified, fixed MDC.
     */
    public static MdcThreadPoolExecutor_old newWithFixedMdc(Map<String, String> fixedContext, int corePoolSize,
                                                        int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                                        BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor_old(fixedContext, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    private MdcThreadPoolExecutor_old(Map<String, String> fixedContext, int corePoolSize, int maximumPoolSize,
                                  long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.fixedContext = fixedContext;
        useFixedContext = (fixedContext != null);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getContextForTask() {
        return useFixedContext ? fixedContext : MDC.getCopyOfContextMap();
    }

    /**
     * All executions will have MDC injected. {@code ThreadPoolExecutor}'s submission methods ({@code submit()} etc.)
     * all delegate to this.
     */
    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, getContextForTask()));
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return new Runnable() {
            @Override
            public void run() {
                Map previous = MDC.getCopyOfContextMap();
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                try {
                    runnable.run();
                } finally {
                    if (previous == null) {
                        MDC.clear();
                    } else {
                        MDC.setContextMap(previous);
                    }
                }
            }
        };
    }
}