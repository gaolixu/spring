package com.rest.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.test.Log1Test;

@Path("/jsonServices")
public class JerseyRestService {
	
	private static final Logger LOGGER = Logger.getLogger(JerseyRestService.class.getName());

	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student produceJSON(@PathParam("name") String name) {
LOGGER.error("test error");
		
		LOGGER.info("test info");
		
		LOGGER.debug("test debug");
		Student st = new Student(1,name, "Diaz", 22);

		return st;

	}

}
