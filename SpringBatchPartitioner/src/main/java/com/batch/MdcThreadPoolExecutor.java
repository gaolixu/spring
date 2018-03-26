package com.batch;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.MDC;





public class MdcThreadPoolExecutor extends ThreadPoolExecutor {

    final private boolean useFixedContext;
    final private Map<String, Object> fixedContext;

    /**
     * Pool where task threads take MDC from the submitting thread.
     */
    public static MdcThreadPoolExecutor newWithInheritedMdc(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                                            TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor(null, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * Pool where task threads take fixed MDC from the thread that creates the pool.
     */
    @SuppressWarnings("unchecked")
    public static MdcThreadPoolExecutor newWithCurrentMdc(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                                          TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor(MDC.getCopyOfContextMap(), corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue);
    }

    /**
     * Pool where task threads always have a specified, fixed MDC.
     */
    public static MdcThreadPoolExecutor newWithFixedMdc(Map<String, Object> fixedContext, int corePoolSize,
                                                        int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                                        BlockingQueue<Runnable> workQueue) {
        return new MdcThreadPoolExecutor(fixedContext, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    private MdcThreadPoolExecutor(Map<String, Object> fixedContext, int corePoolSize, int maximumPoolSize,
                                  long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.fixedContext = fixedContext;
        useFixedContext = (fixedContext != null);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getContextForTask() {
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

    public static Runnable wrap(final Runnable runnable, final Map<String, Object> context) {
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