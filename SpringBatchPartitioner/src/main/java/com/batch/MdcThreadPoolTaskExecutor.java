package com.batch;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

	private static final long serialVersionUID = 1L;
	private boolean useFixedContext = false;
	private Map<String, String> fixedContext;

	public MdcThreadPoolTaskExecutor() {
		super();
	}

	public MdcThreadPoolTaskExecutor(Map<String, String> fixedContext) {
		super();
		this.fixedContext = fixedContext;
		useFixedContext = (fixedContext != null);
	}

	private Map<String, String> getContextForTask() {
		return useFixedContext ? fixedContext : MDC.getCopyOfContextMap();
	}

	/**
	 * All executions will have MDC injected. {@code ThreadPoolExecutor}'s submission methods ({@code submit()} etc.)
	 * all delegate to this.
	 */
	@Override
	public void execute(Runnable command) {
		String threadName = Thread.currentThread().getName();
		super.execute(wrap(command, getContextForTask(),threadName));
	}

	public static Runnable wrap(final Runnable runnable, final Map<String, String> context,final String threadName) {
		return new Runnable() {
			@Override
			public void run() {
				Map<String, String> previous = MDC.getCopyOfContextMap();
				System.out.println("Thread: ------------------------------------"+Thread.currentThread().getName()+" is kicked off by :"+threadName);
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
