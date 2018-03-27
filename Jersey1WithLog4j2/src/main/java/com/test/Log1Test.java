package com.test;

import org.apache.log4j.Logger;

public class Log1Test {
	private static final Logger LOGGER = Logger.getLogger(Log1Test.class.getName());
	
	public static void main(String[] args) {
		LOGGER.error("test error");
		
		LOGGER.info("test info");
		
		LOGGER.debug("test debug");
	}
}
