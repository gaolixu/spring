package com.batch.partition;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class RangePartitioner implements Partitioner {
	
	private static final Logger LOGGER = Logger.getLogger(RangePartitioner.class.getName());

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		//NDC.push("Job Name:" +"testJob");

		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();

		int range = 10;
		int fromId = 1;
		int toId = range;

		for (int i = 1; i <= gridSize; i++) {
			ExecutionContext value = new ExecutionContext();

			/*System.out.println("\nStarting : Thread" + i);
			System.out.println("fromId : " + fromId);
			System.out.println("toId : " + toId);*/
			LOGGER.info("Log4j: Starting : Thread" + i);
			LOGGER.info("Log4j: fromId : " + fromId);
			LOGGER.info("Log4j: toId : " + toId);

			value.putInt("fromId", fromId);
			value.putInt("toId", toId);

			// give each thread a name
			value.putString("name", "Thread" + i);
			

			result.put("partition" + i, value);

			fromId = toId + 1;
			toId += range;

		}

		return result;
	}

}
